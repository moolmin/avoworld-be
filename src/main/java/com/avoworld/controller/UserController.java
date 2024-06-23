package com.avoworld.controller;

import com.avoworld.entity.User;
import com.avoworld.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}/nickname")
    public void updateNickname(@PathVariable Long userId, @RequestBody String nickname) {
        userService.updateNickname(userId, nickname);
    }

    @PutMapping("/{userId}/password")
    public void updatePassword(@PathVariable Long userId, @RequestBody String password) {
        userService.updatePassword(userId, password);
    }

//    @PostMapping("/register")
//    public void registerUser(@RequestBody User user) {
//        userService.registerUser(user);
//    }

    @PostMapping("/{userId}/profileimg")
    public void updateProfilePicture(@PathVariable Long userId, @RequestParam String profilePicture) {
        userService.updateProfilePicture(userId, profilePicture);
    }

    @PostMapping("/check-email")
    public boolean checkEmailDuplicate(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return userService.isEmailDuplicate(email);
    }
}
