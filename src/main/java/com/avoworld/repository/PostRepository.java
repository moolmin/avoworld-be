// PostRepository.java
package com.avoworld.repository;

import com.avoworld.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Post> postRowMapper = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getInt("id"));
        post.setUserId(rs.getInt("user_id"));
        post.setTitle(rs.getString("title"));
        post.setArticle(rs.getString("article"));
        post.setPostPicture(rs.getString("post_picture"));
        post.setEditedPostTitle(rs.getString("edited_post_title"));
        post.setEditedPostContent(rs.getString("edited_post_content"));
        post.setViews(rs.getInt("views"));
        post.setLikes(rs.getInt("likes"));
        post.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
        post.setUpdateAt(rs.getTimestamp("update_at") != null ? rs.getTimestamp("update_at").toLocalDateTime() : null);
        return post;
    };

    public List<Post> findAll() {
        String sql = "SELECT * FROM community_post";
        return jdbcTemplate.query(sql, postRowMapper);
    }

    public Post findById(int postId) {
        String sql = "SELECT * FROM community_post WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, postRowMapper, postId);
    }

    public void deleteById(int postId) {
        String sql = "DELETE FROM community_post WHERE id = ?";
        jdbcTemplate.update(sql, postId);
    }

    public void save(Post post) {
        String sql = "INSERT INTO community_post (title, article, post_picture, user_id, create_at, views, likes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getArticle(), post.getPostPicture(), post.getUserId(), post.getCreateAt(), post.getViews(), post.getLikes());
    }

    public void update(Post post) {
        String sql = "UPDATE community_post SET title = ?, article = ?, post_picture = ?, views = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getArticle(), post.getPostPicture(), post.getViews(), post.getId());
    }

    public void incrementViews(Long postId) {
        String sql = "UPDATE community_post SET views = views + 1 WHERE id = ?";
        jdbcTemplate.update(sql, postId);
    }
}
