package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventTemplateEntityRepositoryQueryImpl implements EventTemplateEntityRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public EventTemplateEntity persist(EventTemplateEntity eventTemplateEntity) {
        UserEntity userEntity = eventTemplateEntity.getUser();

        if (userEntity.getId() == null) {
            throw new PersistenceException("User");
        }
        entityManager.merge(userEntity);
        entityManager.persist(eventTemplateEntity.getAddress());
        entityManager.persist(eventTemplateEntity);

        return eventTemplateEntity;
    }

    @Transactional
    @Override
    public EventTemplateEntity update(EventTemplateEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public List<EventTemplateEntity> listNumberOfBlueprints(int num) {
        return entityManager.createQuery("SELECT b FROM EventTemplateEntity b", EventTemplateEntity.class).setMaxResults(num).getResultList();
    }

    @Override
    public List<EventTemplateEntity> getEventTemplatesForPageNumber(int pageNumber, int pageSize, Long userId) {
       String sqlQuery = "SELECT * FROM event_template WHERE user_id = :user_id ORDER BY created_at DESC";
       Query query = entityManager.createNativeQuery(sqlQuery, EventTemplateEntity.class);

       query.setParameter("user_id", userId);
       query.setFirstResult(pageNumber * pageSize);
       query.setMaxResults(pageSize);

       return query.getResultList();
    }

    @Override
    public EventTemplateEntity getEventTemplateByName(String templateName) {
        return entityManager.createQuery("SELECT t from EventTemplateEntity t where t.name= :name", EventTemplateEntity.class).setParameter("name", templateName).getSingleResult();
    }
}
