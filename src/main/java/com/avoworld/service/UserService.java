package com.avoworld.service;

import com.avoworld.entity.User;
import com.avoworld.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateNickname(Long userId, String nickname) {
        User user = getUserById(userId);
        if (user != null) {
            user.setNickname(nickname);
            userRepository.save(user);
        }
    }

    public void updatePassword(Long userId, String password) {
        User user = getUserById(userId);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    public void updateProfilePicture(Long userId, String profilePicture) {
        User user = getUserById(userId);
        if (user != null) {
            user.setProfilePicture(profilePicture);
            userRepository.save(user);
        }
    }
}
