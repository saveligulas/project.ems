package fhv.team11.project.ems.commons.user;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserEntity findByEmail(String email) {
        String sql = "SELECT * FROM public.user WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        UserEntity userEntity = namedParameterJdbcTemplate.query(sql, params, new UserRowMapper())
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found"));

        String roleSql = "SELECT * FROM user_roles WHERE user_id = :id";
        MapSqlParameterSource roleParams = new MapSqlParameterSource();
        roleParams.addValue("id", userEntity.getId());

        List<Role> roles = namedParameterJdbcTemplate.query(roleSql, roleParams, new RoleRowMapper())
                .stream()
                .toList();

        userEntity.setRoles(roles);

        return userEntity;
    }

    public UserEntity save(UserEntity user) {
        String sql = "INSERT INTO public.user (email, password) VALUES (:email, :password)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});

        Long generatedId = keyHolder.getKey().longValue();
        user.setId(generatedId);

        String roleSql = "INSERT INTO user_roles (user_id, role_ordinal) VALUES (:id, :role_ordinal)";

        for (Role role : user.getRoles()) {
            MapSqlParameterSource paramsRoles = new MapSqlParameterSource();
            paramsRoles.addValue("id", user.getId());
            paramsRoles.addValue("role_ordinal", role.ordinal());

            namedParameterJdbcTemplate.update(roleSql, paramsRoles);
        }

        return user;
    }

    public void update(UserEntity user) {
        String sql = "UPDATE public.user SET email = :email, password = :password WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("id", user.getId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM public.user WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
