// UserRepository.java
package com.avoworld.repository;

import com.avoworld.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setNickname(rs.getString("nickname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setProfilePicture(rs.getString("profile_picture"));
        user.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
        user.setUpdateAt(rs.getTimestamp("update_at") != null ? rs.getTimestamp("update_at").toLocalDateTime() : null);
        return user;
    };


    public List<User> findAll() {
        String sql = "SELECT * FROM community_user";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(Long userId) {
        String sql = "SELECT * FROM community_user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
    }

    public void deleteById(Long userId) {
        String deleteUserSql = "DELETE FROM community_user WHERE user_id = ?";
        String deletePostsSql = "DELETE FROM community_post WHERE user_id = ?";
        jdbcTemplate.update(deleteUserSql, userId);
        jdbcTemplate.update(deletePostsSql, userId);
    }

    public void updateNickname(Long userId, String nickname) {
        String sql = "UPDATE community_user SET nickname = ?, update_at = NOW() WHERE user_id = ?";
        jdbcTemplate.update(sql, nickname, userId);
    }

    public void updatePassword(Long userId, String password) {
        String sql = "UPDATE community_user SET password = ?, update_at = NOW() WHERE user_id = ?";
        jdbcTemplate.update(sql, password, userId);
    }

    public void save(User user) {
        String sql = "INSERT INTO community_user (nickname, email, password, profile_picture, create_at) VALUES (?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, user.getNickname(), user.getEmail(), user.getPassword(), user.getProfilePicture());
    }

    public void updateProfilePicture(Long userId, String profilePicture) {
        String sql = "UPDATE community_user SET profile_picture = ?, update_at = NOW() WHERE user_id = ?";
        jdbcTemplate.update(sql, profilePicture, userId);
    }
}
