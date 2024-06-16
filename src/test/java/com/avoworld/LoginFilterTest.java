//package com.avoworld;
//
//import com.avoworld.jwt.LoginFilter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.avoworld.dto.CustomUserDetails;
//import com.avoworld.entity.User;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletInputStream;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class LoginFilterTest {
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private UserDetailsService userDetailsService;
//
//    @InjectMocks
//    private LoginFilter loginFilter;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAttemptAuthentication() throws IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        when(request.getInputStream()).thenReturn(new ServletInputStream() {
//            private final String json = "{\"email\":\"test@example.com\", \"password\":\"password\"}";
//            private int index = 0;
//
//            @Override
//            public boolean isFinished() {
//                return index >= json.length();
//            }
//
//            @Override
//            public boolean isReady() {
//                return true;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {}
//
//            @Override
//            public int read() throws IOException {
//                if (index >= json.length()) {
//                    return -1;
//                }
//                return json.charAt(index++);
//            }
//        });
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken("test@example.com", "password");
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authToken);
//
//        Authentication result = loginFilter.attemptAuthentication(request, response);
//
//        assertNotNull(result);
//        assertEquals("test@example.com", result.getName());
//        assertEquals("password", result.getCredentials());
//    }
//
//    @Test
//    void testSuccessfulAuthentication() throws IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        FilterChain chain = mock(FilterChain.class);
//        Authentication auth = mock(Authentication.class);
//
//        loginFilter.successfulAuthentication(request, response, chain, auth);
//
//        // You can add more assertions or verifications here
//    }
//
//    @Test
//    void testUnsuccessfulAuthentication() throws IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        AuthenticationException exception = mock(AuthenticationException.class);
//
//        when(exception.getMessage()).thenReturn("Bad credentials");
//
//        loginFilter.unsuccessfulAuthentication(request, response, exception);
//
//        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        verify(response.getWriter()).write("Authentication Failed: Bad credentials");
//    }
//}
