package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.commons.error.PersistException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressRepositoryQueryImpl implements AddressRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address persist(Address entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Address update(Address entity) {
        return null;
    }

    @Override
    public Optional<Address> findById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
    }

    @Override
    public String constructSqlStatement(String action, String clause) {
        return "";
    }
}
