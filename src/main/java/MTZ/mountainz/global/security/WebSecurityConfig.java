package MTZ.mountainz.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration // 설정파일을 만들기 위한 어노테이션 or Bean을 등록하기 위한 어노테이션
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor // 생성자 주입 어노테이션
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.cors().configurationSource(corsConfigurationSource());

        // CORS
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOriginPatterns(List.of("*"));
            cors.setAllowedMethods(List.of("*"));
            cors.setAllowedHeaders(List.of("*"));
            cors.addExposedHeader("Access_Token");
            cors.addExposedHeader("Refresh_Token");
            cors.setAllowCredentials(true);
            return cors;
        });

        http.csrf().disable() // jwt토큰을 사용하기 때문에 csrf 를 풀어도됨
                .exceptionHandling();

        // jwt사용시 필요없음 STATELESS는 사용자 정보를 가지고 있지 않음
        // STATEFUL 은 사용자 정보를 DB에 저장을함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/signUp").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//         /*헤더에 작성된 출처만 브라우저가 리소스를 접근할 수 있도록 허용함.
//        Access-Control-Allow-Origin: "https://naver.com"
//        리소스 접근을 허용하는 HTTP 메서드를 지정해 주는 헤더
//        Access-Control-Request-Methods: GET, POST, PUT, DELETE
//        서버에서 응답 헤더에 추가해 줘야 브라우저의 자바스크립트에서 헤더에 접근 허용
//        Access-Control-Expose-Headers: Authorization
//        preflight 요청 결과를 캐시 할 수 있는 시간을 나타냄.
//        60초 동안 preflight 요청을 캐시하는 설정으로, 첫 요청 이후 60초 동안은 OPTIONS 메소드를 사용하는 예비 요청을 보내지 않는다.
//        Access-Control-Max-Age: 60
//        자바스크립트 요청에서 credentials가 include일 때 요청에 대한 응답을 할 수 있는지를 나타낸다
//        Access-Control-Allow-Credentials: true*/
//
//
//        //프론트쪽 URL기재 필수!
//        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//
//        configuration.setAllowedMethods(Arrays.asList("*"));
//
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//
//        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh_Token"));
//
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
}
