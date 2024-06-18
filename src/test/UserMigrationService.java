//package com.avoworld.service;
//
//import com.avoworld.entity.User;
//import com.avoworld.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class UserMigrationService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @PostConstruct
//    @Transactional
//    public void migrateUsers() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            // 비밀번호가 암호화되어 있지 않은 경우에만 암호화
//            if (!user.getPassword().startsWith("$2a$")) {
//                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//                userRepository.update(user);
//            }
//        }
//    }
//}
