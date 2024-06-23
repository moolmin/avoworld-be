package com.avoworld.jwt;

// JWT 검증 필터
// filter chain에 요청에 담긴 JWT를 검증하기 위한 커스텀 필터 등록하기
// 세션은 stateless로 관리되기 때문에 해당 요청이 끝나면 소멸됨
import com.avoworld.dto.CustomUserDetails;
import com.avoworld.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 요청에 대해 한번만 동작하는 필터
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 회원가입 요청에는 토큰 요청 제외
        if ("/api/join".equals(requestURI) || "/api/accounts/check-email".equals(requestURI) || requestURI.startsWith("/uploads/")) {
            System.out.println("Bypassing JWT filter for: " + requestURI); // Log the bypassing
            filterChain.doFilter(request, response);
            return;
        }

        //request에서 Authorization 헤더 찾기
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null or does not start with Bearer");
            filterChain.doFilter(request, response);

            // 조건 해당되면 메소드 종료
            return;
        }

        // 이제 토큰에서 정보 떼오기
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            // filterChain으로 방금 받은 요청, 응답 토스
            filterChain.doFilter(request, response);

            // 조건 해당되면 메소드 종료
            return;
        }

        // 위에서 두개의 조건문을 통해 토큰을 확인함
        // 토큰에서 이메일 획득
        String email = jwtUtil.getEmail(token);
//        System.out.println("User email from token: " + email);

        //userEntity를 생성해서 값 set
        User user = new User();
        user.setEmail(email);
        user.setPassword("temppassword");

        //userDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken  = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //다음 필터로 요청, 응답한거 넘겨줌
        filterChain.doFilter(request, response);
    }
}
