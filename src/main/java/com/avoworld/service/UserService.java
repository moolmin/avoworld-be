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
        userRepository.updateNickname(userId, nickname);
    }

    public void updatePassword(Long userId, String password) {
        userRepository.updatePassword(userId, password);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void updateProfilePicture(Long userId, String profilePicture) {
        userRepository.updateProfilePicture(userId, profilePicture);
    }
}
