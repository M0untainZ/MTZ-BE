package MTZ.mountainz.domain.member.service;


import MTZ.mountainz.domain.member.dto.request.LoginRequestDto;
import MTZ.mountainz.domain.member.dto.request.MemberRequest;
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

    @Transactional
    public ResponseDto<String> signup(MemberRequest memberRequest) {

        if(memberRepository.findByEmail(memberRequest.getEmail()).isPresent()){
            throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
        }
        memberRequest.setEncodedPwd(passwordEncoder.encode(memberRequest.getPassword()));
        Member member = new Member(memberRequest);

        memberRepository.save(member);
        return ResponseDto.success("회원가입 완료");
    }

    // 로그인
    @Transactional
    public ResponseDto<String> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 이메일 있는지 확인
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404)
        );
        // 비밀번호 있는지 확인
        if(passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new RequestException(ErrorCode.PASSWORD_NOT_FOUND_404);
        }

        // 엑세스토콘, 리프레쉬토큰 생성
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

        // 리프레쉬 토큰은 DB에서 찾기
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(loginRequestDto.getEmail());

        // 리프레쉬토큰 null인지 아닌지 에 따라서
        // 값을 가지고있으면 save
        // 값이 없으면 newToken 만들어내서 save
        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return ResponseDto.success("로그인 성공");
    }

    // response에 담는 메서드
    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
