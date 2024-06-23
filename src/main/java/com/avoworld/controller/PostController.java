package com.avoworld.controller;

import com.avoworld.entity.Post;
import com.avoworld.entity.Comment;
import com.avoworld.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final ObjectMapper objectMapper;

    public PostController(PostService postService, ObjectMapper objectMapper) {
        this.postService = postService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable("postId") Long postId) {
        return postService.getPostById(postId.intValue());
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId.intValue());
    }

    @PostMapping(consumes = "multipart/form-data")
    public void createPost(@RequestParam("file") MultipartFile file, @RequestParam("post") String postJson) throws IOException {
        Post post = objectMapper.readValue(postJson, Post.class);
        postService.createPost(post, file);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Long postId, @RequestBody Post post) {
        post.setId(postId.intValue());
        postService.updatePost(post);
    }

    @PutMapping("/{postId}/views")
    public void incrementPostViews(@PathVariable("postId") Long postId) {
        postService.incrementPostViews(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return postService.getCommentsByPostId(postId);
    }


    @PostMapping("/{postId}/comments")
    public void createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId.intValue());
        postService.createComment(comment);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable("commentId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody Comment comment) {
        comment.setId(commentId.intValue());
        comment.setPostId(postId.intValue());
        postService.updateComment(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        postService.deleteComment(commentId);
    }

//    @PostMapping("/{postId}/image")
//    public String uploadPostImage(@PathVariable("postId") Long postId, @RequestParam("postImage") MultipartFile postImage) throws IOException {
//        if (postImage.isEmpty()) {
//            throw new IllegalArgumentException("No file uploaded");
//        }
//        String fileName = System.currentTimeMillis() + "-" + postImage.getOriginalFilename();
//        String uploadDir = "uploads/";
//        Files.createDirectories(Paths.get(uploadDir));
//        Files.write(Paths.get(uploadDir + fileName), postImage.getBytes());
//        return "/uploads/" + fileName;
//    }
}
