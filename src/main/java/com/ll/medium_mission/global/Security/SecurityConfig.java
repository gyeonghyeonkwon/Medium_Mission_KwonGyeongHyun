package com.ll.medium_mission.global.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                /**
                 *  해당 사이트 로그인 하지 않으면 url 로 접속 불가
                 */
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers( "/member/list" ,"/member/write")
                        .authenticated()
                        .anyRequest().permitAll()

                )


                .headers(
                        headers ->
                                headers.frameOptions(
                                        frameOptions ->
                                                frameOptions.sameOrigin()
                                )
                )
                .csrf(
                        csrf ->
                                csrf.ignoringRequestMatchers(
                                        new AntPathRequestMatcher("/h2-console/**")
                                )
                )

                /**
                 *  로그인 시 게시판 목록으로 이동
                 */
                .formLogin((formLogin) -> formLogin
                        .loginPage("/member/login")
                        .usernameParameter("nickname") // 기본 user -> id 로 변경
                        .defaultSuccessUrl("/member/list"))
                /**
                 * 로그아웃 시 로그인페이지 이동
                 */
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)) //로그 아웃시 세션 삭제
        ;



        http.exceptionHandling(Exception ->Exception
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));


        return http.build();
    }
    //패스워드 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}