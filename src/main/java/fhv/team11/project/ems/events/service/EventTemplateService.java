package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.commons.error.BackEndError;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.services.IHandleSearchQueries;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.EventTemplateDomainDatabaseFactory;
import fhv.team11.project.ems.events.mapper.presentation.EventTemplateDTOMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventTemplateListViewDatabaseMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventTemplateViewMapper;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.repo.EventTemplateEntityRepository;

import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListView;
import fhv.team11.project.ems.events.transfer.EventTemplateView;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.user.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Validated
public class EventTemplateService implements IHandleSearchQueries {
    private static final String SEARCH_DELIMITER = " ";

    private final EventTemplateEntityRepository eventTemplateEntityRepository;
    private final EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory;

    @Autowired
    public EventTemplateService(EventTemplateEntityRepository eventTemplateEntityRepository, EventTemplateDomainDatabaseFactory domainDatabaseFactory, EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory) {
        this.eventTemplateEntityRepository = eventTemplateEntityRepository;
        this.eventTemplateDomainDatabaseFactory = eventTemplateDomainDatabaseFactory;
    }

    private boolean hasAccess(EventTemplateEntity eventTemplateEntity) {
        return eventTemplateEntity.getUser().getId().equals(JwtSecurityContextHolder.getUser().getId());
    }

    public void createNewTemplate(EventTemplateDTO eventTemplateDTO) throws DomainValidationException {
        eventTemplateDomainDatabaseFactory.persist(EventTemplateDTOMapper.INSTANCE.getDomain(eventTemplateDTO));
    }

    public List<EventTemplateListView> getTemplateListViewsPaginated(int pageNumber, int pageSize) {
        return eventTemplateEntityRepository.findAllWithActiveEvents(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(EventTemplateListViewDatabaseMapper.INSTANCE::getView)
                .toList();
    }

    public List<EventTemplateListView> getTemplateListViewsPaginated(int pageNumber, int pageSize, String searchTerm) {
        Specification<EventTemplateEntity> spec = createSearchSpecification(searchTerm);
        return eventTemplateEntityRepository.findAll(spec, PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(EventTemplateListViewDatabaseMapper.INSTANCE::getView)
                .toList();
    }

    private Specification<EventTemplateEntity> createSearchSpecification(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                return null;
            }

            String[] searchTerms = Arrays.stream(searchTerm.split(SEARCH_DELIMITER))
                    .map(String::trim)
                    .filter(term -> !term.isEmpty())
                    .toArray(String[]::new);

            List<Predicate> predicates = new ArrayList<>();

            for (String term : searchTerms) {
                List<Predicate> singleTermPredicates = new ArrayList<>();
                String pattern = "%" + term.toLowerCase().trim() + "%";

                // Search in EventTemplateEntity fields
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("category").as(String.class)), pattern));

                // Search by price range //TODO: add more defined specifications to put in search query
                try {
                    BigDecimal priceValue = new BigDecimal(term);
                    singleTermPredicates.add(criteriaBuilder.equal(
                            root.get("price"), priceValue));
                } catch (NumberFormatException ignored) {}

                // Search by participant numbers
                try {
                    int numValue = Integer.parseInt(term);
                    singleTermPredicates.add(criteriaBuilder.equal(
                            root.get("minParticipants"), numValue));
                    singleTermPredicates.add(criteriaBuilder.equal(
                            root.get("maxParticipants"), numValue));
                    singleTermPredicates.add(criteriaBuilder.equal(
                            root.get("overbookingPlaces"), numValue));
                } catch (NumberFormatException ignored) {}

                // Search in AddressEntity fields
                Join<EventTemplateEntity, AddressEntity> addressJoin = root.join("address", JoinType.LEFT);
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("country")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("region")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("city")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("street")), pattern));

                // Search in UserEntity fields //TODO: later change this to event organizer
                Join<EventTemplateEntity, UserEntity> userJoin = root.join("user", JoinType.LEFT);
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(userJoin.get("username")), pattern));
                // Add more user fields as needed

                // Add the OR condition for all predicates of this term
                predicates.add(criteriaBuilder.or(
                        singleTermPredicates.toArray(new Predicate[0])));
            }

            // Combine all terms with AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public List<EventTemplateListView> getListOfTemplatesFromUser(int pageNumber, int pageSize) {
        return eventTemplateEntityRepository.getEventTemplatesForPageNumber(pageNumber, pageSize, JwtSecurityContextHolder.getUser().getId())
                .stream()
                .map(EventTemplateListViewDatabaseMapper.INSTANCE::getView)
                .toList();
    }

    public EventTemplateListDTO getTemplateListByID(Long templateId) {
        return eventTemplateEntityRepository.findById(templateId)
                .map(EventTemplateListDTODatabaseMapper.INSTANCE::getView)
                .orElse(null);
    }
    public EventTemplateDTO getTemplateByName(String name) {
        //TODO: fix
        // return EventTemplateDTOMapper.INSTANCE.getDomain(eventTemplateRepository.getEventTemplateByName(name));
        return null;
    }

    public EventTemplateView getEventTemplateViewById(Long templateId) {
        EventTemplateEntity eventTemplateEntity = eventTemplateEntityRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(EventTemplateEntity.class, templateId));

        try {
            EventTemplateView view = EventTemplateViewMapper.INSTANCE.getView(eventTemplateDomainDatabaseFactory.toDomain(eventTemplateEntity));
            view.setBelongsToUser(JwtSecurityContextHolder.getUser().getId().equals(eventTemplateEntity.getUser().getId()));
            return view;
        } catch(DomainValidationException e) {
            throw new BackEndError("Unfinished Event Template in database was accessed");
        }
    }
}
