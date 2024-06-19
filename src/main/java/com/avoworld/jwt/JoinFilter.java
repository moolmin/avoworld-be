package com.avoworld.jwt;

import com.avoworld.dto.CustomUserDetails;
import com.avoworld.entity.User;
import com.avoworld.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JoinFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthService authService;
    private final JWTUtil jwtUtil;

    public JoinFilter(String url, AuthenticationManager authenticationManager, @Lazy AuthService authService, JWTUtil jwtUtil) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // Ensure the request's content type is application/json
        if (!"application/json".equalsIgnoreCase(request.getContentType())) {
            throw new IllegalArgumentException("Request content type is not application/json");
        }

        Map<String, String> userMap;
        try {
            userMap = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);
        } catch (IOException e) {
            throw new ServletException("Failed to read request payload", e);
        }

        // Extract user information
        String email = userMap.get("email");
        String password = userMap.get("password");
        String nickname = userMap.get("nickname");
        String profilePicture = userMap.get("profile_picture");
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setNickname(nickname);
        newUser.setProfilePicture(profilePicture);
        authService.registerUser(newUser);

        // Authenticate the user
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        String email = customUserDetails.getUsername();

        // Generate JWT token
        String token = jwtUtil.createJwt(email, 60 * 60 * 24 * 1000L); // 24 hours validity
        response.addHeader("Authorization", "Bearer " + token);

        // Respond with JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) {
        res.setStatus(401);
    }
}
