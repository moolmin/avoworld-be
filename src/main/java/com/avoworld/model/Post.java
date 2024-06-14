package com.avoworld.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "community_post")
public class Post {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(String postPicture) {
        this.postPicture = postPicture;
    }

    public String getEditedPostTitle() {
        return editedPostTitle;
    }

    public void setEditedPostTitle(String editedPostTitle) {
        this.editedPostTitle = editedPostTitle;
    }

    public String getEditedPostContent() {
        return editedPostContent;
    }

    public void setEditedPostContent(String editedPostContent) {
        this.editedPostContent = editedPostContent;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String title;
    private String article;
    private String postPicture;
    private String editedPostTitle;
    private String editedPostContent;
    private int views;
    private int likes;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Getters and Setters
}
