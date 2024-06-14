package com.avoworld.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
public class Comment {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getEditedCommentContent() {
        return editedCommentContent;
    }

    public void setEditedCommentContent(String editedCommentContent) {
        this.editedCommentContent = editedCommentContent;
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
    private int postId;
    private int userId;
    private String commentContent;
    private String editedCommentContent;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Getters and Setters
}
