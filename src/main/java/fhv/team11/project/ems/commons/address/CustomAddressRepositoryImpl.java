package fhv.team11.project.ems.commons.address;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomAddressRepositoryImpl implements CustomAddressRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomAddressRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Address> findById() {
        return Optional.empty();
    }

    @Override
    public void persist(Address address) {
    }
}
