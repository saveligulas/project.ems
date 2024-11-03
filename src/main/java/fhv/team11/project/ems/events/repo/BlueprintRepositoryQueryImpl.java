package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.error.DatabaseException;
import fhv.team11.project.ems.commons.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class BlueprintRepositoryQueryImpl implements BlueprintRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Blueprint blueprint) throws DatabaseException {
        User user = blueprint.getUser();

        if (user.getId() == null) {
            throw new DatabaseException("User");
        }
        entityManager.merge(user);
        entityManager.persist(blueprint.getAddress());
        entityManager.persist(blueprint);
    }
}
