package com.avoworld.jwt;

import com.avoworld.dto.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(String url, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        super(new AntPathRequestMatcher(url));
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        Map<String, String> credentials = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);

        String email = credentials.get("email");
        String password = credentials.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(token);
    }

    // 이제 로그인 성공하면 jwt 토큰 발행 ㄱㄱ
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {

        System.out.println("success");

        // 오류뜨면 여기 확인해바!!!!
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        String email = customUserDetails.getUsername();

        String token = jwtUtil.createJwt(email, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed)  {
        res.setStatus(401);
        System.out.println("failure");
    }

}
