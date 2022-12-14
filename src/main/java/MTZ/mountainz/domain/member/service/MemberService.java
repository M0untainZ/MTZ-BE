package MTZ.mountainz.domain.member.service;

import MTZ.mountainz.domain.badge.entity.Badge;
import MTZ.mountainz.domain.badge.entity.MemberBadge;
import MTZ.mountainz.domain.badge.repository.BadgeRepository;
import MTZ.mountainz.domain.badge.repository.MemberBadgeRepository;
import MTZ.mountainz.domain.member.dto.request.LoginRequestDto;
import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.member.dto.response.LoginResponseDto;
import MTZ.mountainz.domain.member.dto.response.SignupResponseDto;
import MTZ.mountainz.domain.member.entity.Authority;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.entity.RefreshToken;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.member.repository.RefreshTokenRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.dto.TokenDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import MTZ.mountainz.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberBadgeRepository memberBadgeRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public ResponseDto<SignupResponseDto> signup(MemberRequestDto memberRequestDto) {

        if (memberRepository.findByEmail(memberRequestDto.getEmail()).isPresent()) {
            throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
        }
        memberRequestDto.setEncodedPwd(passwordEncoder.encode(memberRequestDto.getPassword()));
        Member member = new Member(memberRequestDto, Authority.ROLE_USER);

        memberRepository.save(member);

        boolean correctBadge = true;

        Badge badge = badgeRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException()
        );

        memberBadgeRepository.save(new MemberBadge(badge, member));
        return ResponseDto.success(
                SignupResponseDto.builder()
                        .badge(badge)
                        .correctBadge(correctBadge)
                        .build()
        );
    }

    // ?????????
    @Transactional
    public ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // ????????? ????????? ??????
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new RequestException(ErrorCode.LOGIN_NOT_FOUND_404)
        );
        // ???????????? ????????? ??????
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new RequestException(ErrorCode.LOGIN_NOT_FOUND_404);
        }

        // ???????????????, ?????????????????? ??????
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

        // ???????????? ????????? DB?????? ??????
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(loginRequestDto.getEmail());

        // ?????????????????? null?????? ????????? ??? ?????????
        // ?????? ?????????????????? save
        // ?????? ????????? newToken ??????????????? save
        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return ResponseDto.success(
                LoginResponseDto.builder()
                        .nickName(member.getNickName())
                        .badgeName(member.getBadgeName())
                        .profilePhoto(member.getProfilePhoto())
                        .region(member.getRegion())
                        .authority(member.getAuthority())
                        .build()
        );
    }

    // response??? ?????? ?????????
    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    // ????????? ?????? ??????
    public ResponseDto<String> emailConfirm(String email) {
        if (memberRepository.existsByEmail(email)) {
            return ResponseDto.success(false, "?????? ?????? ??????????????????.");
        } else {
            return ResponseDto.success("?????? ????????? ??????????????????.");
        }
    }

    // ????????? ?????? ??????
    public ResponseDto<String> nickNameConfirm(String nickName) {
        if (memberRepository.existsByNickName(nickName)) {
            return ResponseDto.success(false, "?????? ?????? ??????????????????.");
        } else {
            return ResponseDto.success("?????? ????????? ??????????????????.");
        }
    }
}