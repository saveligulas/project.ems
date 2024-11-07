package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.user.repo.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
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


    @Override
    public List<Blueprint> listNumberOfBlueprints(int num) {

        return entityManager.createQuery("SELECT b FROM Blueprint b", Blueprint.class).setMaxResults(num).getResultList();
    }
}
