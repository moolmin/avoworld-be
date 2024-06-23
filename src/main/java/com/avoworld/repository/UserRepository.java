// UserRepository.java
package com.avoworld.repository;

import com.avoworld.entity.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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

    public User findByEmail(String email) {
        String sql = "SELECT * FROM community_user WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, email);

        if (users.size() == 1) {
            return users.get(0);
        } else if (users.isEmpty()) {
            return null; // 또는 적절한 예외를 던질 수 있습니다.
        } else {
            throw new IncorrectResultSizeDataAccessException(1, users.size());
        }
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

    public void update(User user) {
        String sql = "UPDATE community_user SET nickname = ?, email = ?, password = ?, profile_picture = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getNickname(), user.getEmail(), user.getPassword(), user.getProfilePicture(), user.getUserId());
    }


    public void updateProfilePicture(Long userId, String profilePictureUrl) {
        String sql = "UPDATE community_user SET profile_picture = ?, updated_at = NOW() WHERE user_id = ?";
        jdbcTemplate.update(sql, profilePictureUrl, userId);
    }


    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM community_user WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
