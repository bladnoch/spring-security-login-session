package org.example.security_session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * 원래 스프링 시큐리티를 키면 로그인 페이지가 나오지만 시작 페이지 권한을 열어두었기 때문에
         * 조건없이 main page로 접근이 가능하다
         */
        http
                /**
                 * 깔때기처럼 requestMathcers의 권한은 순서에 따라 주어지고 앞에서의 권한이 뒤어서도 적용되기 때문에
                 * 넓은 범위의 권한부터 좁은 범위의 권한으로 설정해야 한다.
                 */
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll() // 시작 페이지, 로그인 페이지 조건 없이 접근 허용
                        .requestMatchers("/admin").hasRole("ADMIN") // admin page는 role = "ADMIN" 만 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // role 을 가진 경우 접근 허용
                        .anyRequest().authenticated() // 나머지 요청에 관해선 거부
                );

        return http.build();
    }

}
