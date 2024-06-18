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
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "article", columnDefinition="LONGTEXT")
    private String article;

    @Column(name = "post_picture")
    private String postPicture;

    @Column(name = "edited_post_title", nullable = true)
    private String editedPostTitle;

    @Column(name = "edited_post_content")
    private String editedPostContent;

    @Column(name = "views")
    private int views;

    @Column(name = "likes")
    private int likes;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;
}
