package MTZ.mountainz.domain.member.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MTZ.mountainz.domain.member.dto.request.LoginRequestDto;
import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.member.dto.response.LoginResponseDto;
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

@Service
@RequiredArgsConstructor
public class MemberService {

	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional
	public ResponseDto<String> signup(MemberRequestDto memberRequestDto) {

		if (memberRepository.findByEmail(memberRequestDto.getEmail()).isPresent()) {
			throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
		}
		memberRequestDto.setEncodedPwd(passwordEncoder.encode(memberRequestDto.getPassword()));
		Member member = new Member(memberRequestDto, Authority.ROLE_USER);

		memberRepository.save(member);
		return ResponseDto.success("회원가입 완료");
	}

	// 로그인
	@Transactional
	public ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
		// 이메일 있는지 확인
		Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
			() -> new RequestException(ErrorCode.LOGIN_NOT_FOUND_404)
		);
		// 비밀번호 있는지 확인
		if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
			throw new RequestException(ErrorCode.LOGIN_NOT_FOUND_404);
		}

		// 엑세스토콘, 리프레쉬토큰 생성
		TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

		// 리프레쉬 토큰은 DB에서 찾기
		Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(loginRequestDto.getEmail());

		// 리프레쉬토큰 null인지 아닌지 에 따라서
		// 값을 가지고있으면 save
		// 값이 없으면 newToken 만들어내서 save
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
				.region(member.getRegion())
				.authority(member.getAuthority())
				.build()
		);
	}

	// response에 담는 메서드
	private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
		response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
		response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
	}

	// 이메일 중복 확인
	public ResponseDto<String> emailConfirm(String email) {
		if (memberRepository.existsByEmail(email)) {
			return ResponseDto.success(false, "사용 중인 이메일입니다.");
		} else {
			return ResponseDto.success("사용 가능한 이메일입니다.");
		}
	}

	// 닉네임 중복 확인
	public ResponseDto<String> nickNameConfirm(String nickName) {
		if (memberRepository.existsByNickName(nickName)) {
			return ResponseDto.success(false, "사용 중인 닉네임입니다.");
		} else {
			return ResponseDto.success("사용 가능한 닉네임입니다.");
		}
	}
}