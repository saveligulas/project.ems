package fhv.team11.project.ems.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find user by email (as a replacement for findByEmail in JPA)
    public Optional<UserModel> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper())
                .stream()
                .findFirst();
    }

    // Save a new user to the database
    public void save(UserModel user) {
        String sql = "INSERT INTO users (email, password, authority) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getAuthority().ordinal());
    }

    // Update an existing user
    public void update(UserModel user) {
        String sql = "UPDATE users SET email = ?, password = ?, authority = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getAuthority().ordinal(), user.getId());
    }

    // Delete a user by ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
