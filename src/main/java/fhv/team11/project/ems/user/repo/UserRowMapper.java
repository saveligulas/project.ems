package fhv.team11.project.ems.user.repo;

import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserJDBC> {
    @Override
    public UserJDBC mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserJDBC user = new UserJDBC();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
