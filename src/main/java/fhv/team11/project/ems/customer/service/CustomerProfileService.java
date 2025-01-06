package fhv.team11.project.ems.customer.service;

import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTODatabaseMapper;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.role.Role;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.entity.UserJDBC;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerProfileService {

    @Value("${app.search.max-page-size:100}")
    private int maxPageSize;
    @Value("${app.search.default-page-size:10}")
    private int defaultPageSize;
    private final String SEARCH_DELIMITER = " ";
    private final CustomerProfileRepository customerProfileRepository;
    private final UserEntityRepository userEntityRepository;
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository customerProfileRepository,
                                  UserEntityRepository userEntityRepository, CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory) {
        this.customerProfileRepository = customerProfileRepository;
        this.userEntityRepository = userEntityRepository;
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
    }

    public void createNewCustomerProfile(CustomerProfileDTO customerProfileDTO) throws DomainValidationException {
        UserJDBC userJDBC = JwtSecurityContextHolder.getUserJDBC();
        UserEntity user = JwtSecurityContextHolder.getUser();

        CustomerProfile customerProfile = customerProfileDomainDatabaseFactory.persist(CustomerProfileDTOMapper.INSTANCE.getDomain(customerProfileDTO));

        CustomerProfileEntity customerProfileEntity = customerProfileDomainDatabaseFactory.toEntity(customerProfile);

        //TODO: User cant be Customer and BackOffice Employee
        if (userJDBC.getRoles().contains(Role.CUSTOMER)) {
            customerProfileEntity.setUserEntityDetails(user.getUserEntityDetails());
            user.getUserEntityDetails().setCustomerProfileEntity(customerProfileEntity);
            userEntityRepository.save(user);
        }
    }

    public List<CustomerProfileDTO> getListOfCustomerProfiles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CustomerProfileEntity> customerProfilePage = customerProfileRepository.findAll(pageable);

        return customerProfilePage.getContent().stream()
                .map(CustomerProfileDTODatabaseMapper.INSTANCE::getView)
                .toList();
    }

    public Page<CustomerProfileDTO> searchCustomers(String searchTerm, int page, Integer size) {

        // Validate and apply page size
        int validatedPageSize = validateAndGetPageSize(size);
        Pageable pageable = PageRequest.of(page, validatedPageSize);

        // Create and execute search
        Specification<CustomerProfileEntity> spec = createSearchSpecification(searchTerm);
        List<CustomerProfileDTO> dtos = customerProfileRepository.findAll(spec, pageable).getContent()
                .stream()
                .map(CustomerProfileDTODatabaseMapper.INSTANCE::getView)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, customerProfileRepository.count(spec));
    }

    private int validateAndGetPageSize(Integer requestedSize) {
        if (requestedSize == null) {
            return defaultPageSize;
        }
        if (requestedSize <= 0) {
            return defaultPageSize;
        }
        return Math.min(requestedSize, maxPageSize);
    }

    private Specification<CustomerProfileEntity> createSearchSpecification(String searchTerm) {
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

                // Search in CustomerProfileEntity fields
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("id")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("phoneNumber")), pattern));

                // Search in AddressEntity fields
                Join<CustomerProfileEntity, AddressEntity> addressJoin = root.join("addressEntity");
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("country")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("region")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("city")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("street")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("houseNumber")), pattern));
                singleTermPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(addressJoin.get("optionalText")), pattern));

                // Add zip code search with conversion
                singleTermPredicates.add(criteriaBuilder.equal(
                        addressJoin.get("zip"), tryParseInt(term)));

                predicates.add(criteriaBuilder.or(
                        singleTermPredicates.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
