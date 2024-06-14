// PostRepository.java
package com.avoworld.repository;

import com.avoworld.model.Post;
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
}
