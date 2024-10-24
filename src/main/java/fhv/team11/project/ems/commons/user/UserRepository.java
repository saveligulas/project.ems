package fhv.team11.project.ems.commons.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<UserEntity> findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = :email";
        Map<String, Object> params = Collections.singletonMap("email", email);

        return namedParameterJdbcTemplate.query(sql, params, new UserRowMapper())
                .stream()
                .findFirst();
    }

    public UserEntity save(UserEntity user) {
        String sql = "INSERT INTO user (email, password) VALUES (:email, :password, :authority)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});

        Long generatedId = keyHolder.getKey().longValue();
        user.setId(generatedId);

        return user;
    }

    public void update(UserEntity user) {
        String sql = "UPDATE user SET email = :email, password = :password WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("authority", user.getAuthority().ordinal());
        params.put("id", user.getId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
