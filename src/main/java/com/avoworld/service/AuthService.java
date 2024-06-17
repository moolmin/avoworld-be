package com.avoworld.service;

import com.avoworld.entity.User;
import com.avoworld.jwt.JWTUtil;
import com.avoworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String loginUser(User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());
        if (user != null) {
            // 입력된 비밀번호와 암호화된 비밀번호 비교
            if (bCryptPasswordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                return jwtUtil.createJwt(user.getEmail(), 60*60*10L);
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
