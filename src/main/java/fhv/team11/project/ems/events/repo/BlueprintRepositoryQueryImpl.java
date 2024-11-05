package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.error.DatabaseException;
import fhv.team11.project.ems.commons.user.repo.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BlueprintRepositoryQueryImpl implements BlueprintRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Blueprint persist(Blueprint blueprint) {
        User user = blueprint.getUser();

        if (user.getId() == null) {
            throw new PersistenceException("User");
        }
        entityManager.merge(user);
        entityManager.persist(blueprint.getAddress());
        entityManager.persist(blueprint);

        return blueprint;
    }

    @Override
    public Blueprint update(Blueprint entity) {
        return null;
    }

    @Override
    public Optional<Blueprint> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
