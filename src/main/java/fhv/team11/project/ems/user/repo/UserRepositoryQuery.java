package fhv.team11.project.ems.user.repo;

import fhv.team11.project.ems.commons.database.IRepository;
import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import jakarta.persistence.PersistenceException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryQuery implements IRepository<UserJDBC, Long> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryQuery(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserJDBC findByEmail(String email) {
        String sql = "SELECT * FROM public.user WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        UserJDBC userJDBC = namedParameterJdbcTemplate.query(sql, params, new UserRowMapper())
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found"));

        String roleSql = "SELECT * FROM user_roles WHERE user_id = :id";
        MapSqlParameterSource roleParams = new MapSqlParameterSource();
        roleParams.addValue("id", userJDBC.getId());

        List<Role> roles = namedParameterJdbcTemplate.query(roleSql, roleParams, new RoleRowMapper())
                .stream()
                .toList();

        userJDBC.setRoles(roles);

        return userJDBC;
    }

    public UserJDBC save(UserJDBC user) {
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

    @Override
    public UserJDBC persist(UserJDBC entity) {
        return save(entity);
    }

    public UserJDBC update(UserJDBC user) {
        String sql = "UPDATE public.user SET email = :email, password = :password WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());
        params.addValue("id", user.getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});

        return findById(keyHolder.getKey().longValue())
                .orElseThrow(() -> new PersistenceException("Could not retrieve updated user from database"));
    }

    @Override
    public Optional<UserJDBC> findById(Long aLong) {
        String selectSql = "SELECT * FROM public.user WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", aLong);

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(selectSql, params, new UserRowMapper()));
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM public.user WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
