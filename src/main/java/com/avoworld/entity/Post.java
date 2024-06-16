package com.avoworld.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "community_post")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String title;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String article;
    private String postPicture;
    private String editedPostTitle;
    private String editedPostContent;
    private int views;
    private int likes;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
