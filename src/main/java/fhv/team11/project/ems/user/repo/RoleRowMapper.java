package fhv.team11.project.ems.user.repo;

import fhv.team11.project.ems.security.permission.role.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.values()[rs.getInt("role_ordinal")];
    }
}
