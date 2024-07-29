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

        /**
         * 로그인 페이지 설정
         * 서정해놓은 로그인 페이지의 경로를 설정
         * -> 앞으로 어드민 경로에 들어갈 때 설정한 로그인 페이지로 리 다이렉션을 한다.
         */
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        /**
         * csrf 토큰이 있어야 로그인이 진행되는데 이번예제의 경우 csrf 토큰 없이 진행하기 위해 disacble
         */
        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }

}
