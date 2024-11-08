package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.user.repo.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventTemplateRepositoryQueryImpl implements EventTemplateRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public EventTemplate persist(EventTemplate eventTemplate) {
        User user = eventTemplate.getUser();

        if (user.getId() == null) {
            throw new PersistenceException("User");
        }
        entityManager.merge(user);
        entityManager.persist(eventTemplate.getAddress());
        entityManager.persist(eventTemplate);

        return eventTemplate;
    }

    @Override
    public EventTemplate update(EventTemplate entity) {
        return null;
    }

    @Override
    public Optional<EventTemplate> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public List<EventTemplate> listNumberOfBlueprints(int num) {

        return entityManager.createQuery("SELECT b FROM EventTemplate b", EventTemplate.class).setMaxResults(num).getResultList();
    }

    @Override
    public EventTemplate getEventTemplateByName(String templateName) {
        return entityManager.createQuery("SELECT t from EventTemplate t where t.name= :name", EventTemplate.class).setParameter("name", templateName).getSingleResult();
    }
}
