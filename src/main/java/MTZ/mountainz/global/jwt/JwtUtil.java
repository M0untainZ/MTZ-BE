package MTZ.mountainz.global.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import MTZ.mountainz.domain.member.entity.Authority;
import MTZ.mountainz.domain.member.entity.RefreshToken;
import MTZ.mountainz.domain.member.repository.RefreshTokenRepository;
import MTZ.mountainz.global.dto.TokenDto;
import MTZ.mountainz.global.security.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

	private final UserDetailsServiceImpl userDetailsService;
	private final RefreshTokenRepository refreshTokenRepository;

	private static final String AUTHORITIES_KEY = "auth";
	public static final String ACCESS_TOKEN = "Authorization";
	public static final String REFRESH_TOKEN = "Refresh_Token";
	private static final long ACCESS_TIME = 1000 * 60 * 60 * 24;
	private static final long REFRESH_TIME = 1000 * 60 * 60 * 24 * 7;

	public static final String BEARER_TYPE = "Bearer ";

	@Value("${jwt.secret}")
	private String secretKey;
	private Key key;
	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	@PostConstruct
	public void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);
	}

	public String getHeaderToken(HttpServletRequest request, String type) {
		String bearerToken = type.equals("Access") ? request.getHeader(ACCESS_TOKEN) : request.getHeader(REFRESH_TOKEN);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	// 토큰 생성
	public TokenDto createAllToken(String email) {
		return new TokenDto(createToken(email, "Access"), createToken(email, "Refresh"));
	}

	public String createToken(String email, String type) {
		Date date = new Date();

		long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

		return BEARER_TYPE + Jwts.builder()
			.setSubject(email)
			.claim(AUTHORITIES_KEY, Authority.ROLE_USER.toString())
			.setExpiration(new Date(date.getTime() + time))
			.setIssuedAt(date)
			.signWith(key, signatureAlgorithm)
			.compact();
	}

	// 토큰 검증
	public Boolean tokenValidation(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token, 만료된 JWT token 입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
		}
		return false;
	}

	// refreshToken 토큰 검증
	public Boolean refreshTokenValidation(String token) {

		// 1차 토큰 검증
		if (!tokenValidation(token))
			return false;

		// DB에 저장한 토큰 비교
		Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(getEmailFromToken(token));

		return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
	}

	// 인증 객체 생성
	public Authentication createAuthentication(String email) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	// 토큰에서 email 가져오는 기능
	public String getEmailFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
}


