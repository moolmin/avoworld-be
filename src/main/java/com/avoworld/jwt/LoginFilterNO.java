//package com.avoworld.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.Map;
//
//
//// 이제 SecurityConfig에 로그인필터 등록
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    public LoginFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
//        String email;
//        String password;
//
//        try {
//            Map<String, String> requestBody = new ObjectMapper().readValue(req.getInputStream(), Map.class);
//            email = requestBody.get("email");
//            password = requestBody.get("password");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        System.out.println("email: " + email);
//        System.out.println("password: " + password);
//
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
//
//        return authenticationManager.authenticate(authToken);
//    }
//
//    @Override
//    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
//
//        System.out.println("success");
//    }
//
//    @Override
//    public void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException {
//        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        res.getWriter().write("Authentication Failed: " + failed.getMessage());
//        System.out.println("fail");
//    }
//
//}
