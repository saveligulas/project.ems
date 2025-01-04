package fhv.team11.project.ems.customer;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@NullMarked
public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Long>, JpaSpecificationExecutor<CustomerProfileEntity> {

    Page<CustomerProfileEntity> findAll(Pageable pageable);

    Page<CustomerProfileEntity> findAll(Specification<CustomerProfileEntity> spec, Pageable pageable);
}
