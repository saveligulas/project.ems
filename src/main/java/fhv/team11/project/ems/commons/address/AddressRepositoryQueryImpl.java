package fhv.team11.project.ems.commons.address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryQueryImpl implements AddressRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AddressEntity persist(AddressEntity entity) {
        if (entity == null || entity.getId() != null) {
            throw new PersistenceException();
        }
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public AddressEntity update(AddressEntity entity) {
        return null;
    }
}
