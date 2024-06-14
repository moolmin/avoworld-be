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
}
