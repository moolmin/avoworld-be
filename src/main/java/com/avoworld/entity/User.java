package com.avoworld.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "community_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String nickname;
    private String email;
    private String password;
    private String profilePicture;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String role;
}
