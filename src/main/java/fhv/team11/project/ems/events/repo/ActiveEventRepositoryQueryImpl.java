package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ActiveEventRepositoryQueryImpl implements ActiveEventRepositoryQuery {

    private final EventTemplateRepository eventTemplateRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ActiveEventRepositoryQueryImpl(EventTemplateRepository eventTemplateRepository) {
        this.eventTemplateRepository = eventTemplateRepository;
    }

    @Transactional
    @Override
    public ActiveEvent persist(ActiveEvent entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public ActiveEvent update(ActiveEvent entity) {
        return null;
    }
}
