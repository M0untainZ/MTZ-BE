package MTZ.mountainz.domain.member.service;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import MTZ.mountainz.domain.member.dto.request.KakaoUserInfoDto;
import MTZ.mountainz.domain.member.entity.Authority;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.entity.RefreshToken;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.member.repository.RefreshTokenRepository;
import MTZ.mountainz.global.dto.TokenDto;
import MTZ.mountainz.global.jwt.JwtUtil;
import MTZ.mountainz.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoUserService {
	@Value("${Kakao.client-id}")
	String clientId;
	@Value("${Kakao.redirect-uri}")
	String redirectUri;

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtUtil jwtUtil;

	public KakaoUserInfoDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {

		// 1. "인가 코드"로 "액세스 토큰" 요청
		String accessToken = getAccessToken(code);

		// 2. 토큰으로 카카오 API 호출
		KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

		// 3. 카카오 ID 로 회원가입 처리
		Member kakaoUser = signupKakaoUser(kakaoUserInfo);

		// 4. 강제 로그인 처리
		forceLogin(kakaoUser);

		// 5. response Header 에 JWT 토큰 추가
		TokenDto tokenDto = jwtUtil.createAllToken(kakaoUserInfo.getId().toString());

		Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(kakaoUser.getId());

		if (refreshToken.isPresent()) {
			refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
		} else {
			RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), kakaoUserInfo.getId().toString());
			refreshTokenRepository.save(newToken);
		}
		setHeader(response, tokenDto);
		// 토큰 생성 후 tokenDto 에 저장
		return kakaoUserInfo;
	}

	// 1. "인가 코드"로 "액세스 토큰" 요청
	private String getAccessToken(String code) throws JsonProcessingException {
		// HTTP Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// HTTP Body 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", clientId);
		body.add("redirect_uri", redirectUri);
		body.add("code", code);
		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		// HTTP 응답 (JSON) -> 액세스 토큰 파싱
		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		return jsonNode.get("access_token").asText();
	}

	// 2. 토큰으로 카카오 API 호출
	private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
		// HTTP Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoUserInfoRequest,
			String.class
		);
		// responseBody 에 있는 정보를 꺼냄
		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		Long id = jsonNode.get("id").asLong();
		String nickname = jsonNode.get("properties").get("nickname").asText();

		return new KakaoUserInfoDto(id, nickname);
	}

	// 3. 카카오 ID 로 회원가입 처리
	private Member signupKakaoUser(KakaoUserInfoDto kakaoUserInfo) {
		// DB 에 중복된 email 이 있는지 확인
		String kakaoId = kakaoUserInfo.getId().toString();
		String nickname = kakaoUserInfo.getNickname();
		Member kakaoUser = memberRepository.findByKakaoId(kakaoId)
			.orElse(null);

		if (kakaoUser == null) {
			// 회원가입
			// password: random UUID
			String password = UUID.randomUUID().toString();
			String encodedPassword = passwordEncoder.encode(password);
			String region = "카카오";

			kakaoUser = new Member(kakaoId, encodedPassword, nickname, region, Authority.ROLE_USER);
			memberRepository.save(kakaoUser);
		}
		return kakaoUser;
	}

	// 4. 강제 로그인 처리
	private Authentication forceLogin(Member kakaoUser) {
		UserDetails userDetails = new UserDetailsImpl(kakaoUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
			userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	// 5. response Header 에 JWT 토큰 추가
	private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
		response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
		response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
	}
}
