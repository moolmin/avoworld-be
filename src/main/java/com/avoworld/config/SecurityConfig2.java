//package com.avoworld.config;
//
//import com.avoworld.jwt.LoginFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig2 {
//
//    private final AuthenticationConfiguration authenticaitonConfiguration;
//
//    public SecurityConfig2(AuthenticationConfiguration authenticaitonConfiguration) {
//        this.authenticaitonConfiguration = authenticaitonConfiguration;
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf((auth) -> auth.disable());
//
//        http
//                .formLogin((auth) -> auth.disable());
//
//        http
//                .httpBasic((auth) -> auth.disable());
//
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login", "/", "/join", "/api/accounts").permitAll()
//                        .anyRequest().authenticated());
//
//        http
//                .addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
//
//
//        // 세션 설정
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//}