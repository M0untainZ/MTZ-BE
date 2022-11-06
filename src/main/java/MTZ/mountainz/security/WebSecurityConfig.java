package MTZ.mountainz.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration // 설정파일을 만들기 위한 어노테이션 or Bean을 등록하기 위한 어노테이션
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor // 생성자 주입 어노테이션
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable() // jwt토큰을 사용하기 때문에 csrf 를 풀어도됨
                .exceptionHandling();

        // jwt사용시 필요없음 STATELESS는 사용자 정보를 가지고 있지 않음
        // STATEFULL 은 사용자 정보를 DB에 저장을함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/signUp").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
