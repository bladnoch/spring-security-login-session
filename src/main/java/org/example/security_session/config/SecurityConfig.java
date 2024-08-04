package org.example.security_session.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    시큐리티 암호와 메소드: 암호화를 위한 메소드로 bean 등록을 해서 사용
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
                        .requestMatchers("/","/loginProc","/login","/joinProc","/join").permitAll() // 시작 페이지, 로그인 페이지 조건 없이 접근 허용
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
         * 아래의 코드 작성을 하지 않을 경우 default로 enable 상태가 되기 때문에 토큰 검증 부분을 만들어 주어야 한다
         * ch.11에서 위의 설정을 변경함
         */
//        http
//                .csrf((auth) -> auth.disable());

        // 다중 로그인 관리
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 동시 접속 가능 숫자: 1개 허용
                        .maxSessionsPreventsLogin(true)); // true: 초과시 차단, false: 초과시 세션 권한 하나 삭제

        // 세션 고정 공격 보호
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation()
                        .changeSessionId());

        return http.build();
    }

    // inMemory 방식 유저 정보 저장
    // 사용시 UserDetailsService를 주석처리 해야 사용 가능
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.builder()
//                .username("user1")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("user2")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//
//    }

}
