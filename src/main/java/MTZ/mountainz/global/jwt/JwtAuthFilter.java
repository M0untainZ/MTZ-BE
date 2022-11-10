package MTZ.mountainz.global.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import MTZ.mountainz.global.dto.GlobalResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String accessToken = jwtUtil.getHeaderToken(request, "Access");
		String refreshToken = jwtUtil.getHeaderToken(request, "Refresh");

		if (accessToken != null) {
			if (!jwtUtil.tokenValidation(accessToken)) {
				jwtExceptionHandler(response, "AccessToken Expired", HttpStatus.BAD_REQUEST);
				return;
			}
			setAuthentication(jwtUtil.getEmailFromToken(accessToken));
		} else if (refreshToken != null) {
			if (!jwtUtil.refreshTokenValidation(refreshToken)) {
				jwtExceptionHandler(response, "RefreshToken Expired", HttpStatus.BAD_REQUEST);
				return;
			}
			setAuthentication(jwtUtil.getEmailFromToken(refreshToken));
		}

		// 다음 필터로 수행되게 해준다
		filterChain.doFilter(request, response);
	}

	public void setAuthentication(String email) {
		Authentication authentication = jwtUtil.createAuthentication(email);
		// context에 담아두면 UsernamePasswordAuthenticationFilter에서 인증된걸 알게 해준다
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus status) {
		response.setStatus(status.value());
		response.setContentType("application/json");
		try {
			String json = new ObjectMapper().writeValueAsString(new GlobalResDto(msg, status.value()));
			response.getWriter().write(json);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
