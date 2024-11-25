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

    @Transactional
    @Override
    public EventTemplate update(EventTemplate entity) {
        return entityManager.merge(entity);
    }

    @Override
    public List<EventTemplate> listNumberOfBlueprints(int num) {
        return entityManager.createQuery("SELECT b FROM EventTemplate b", EventTemplate.class).setMaxResults(num).getResultList();
    }

    @Override
    public List<EventTemplate> getEventTemplatesForPageNumber(int pageNumber, int pageSize, Long userId) {
       String sqlQuery = "SELECT * FROM event_template WHERE user_id = :user_id ORDER BY created_at DESC";
       Query query = entityManager.createNativeQuery(sqlQuery, EventTemplate.class);

       query.setParameter("user_id", userId);
       query.setFirstResult(pageNumber * pageSize);
       query.setMaxResults(pageSize);

       return query.getResultList();
    }

    @Override
    public EventTemplate getEventTemplateByName(String templateName) {
        return entityManager.createQuery("SELECT t from EventTemplate t where t.name= :name", EventTemplate.class).setParameter("name", templateName).getSingleResult();
    }
}
