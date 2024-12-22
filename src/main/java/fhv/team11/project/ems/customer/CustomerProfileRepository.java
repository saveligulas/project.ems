package fhv.team11.project.ems.customer;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Long> {
    Page<CustomerProfileEntity> findAll(@NotNull Pageable pageable);
}
