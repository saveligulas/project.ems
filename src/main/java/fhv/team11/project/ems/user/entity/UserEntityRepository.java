package fhv.team11.project.ems.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
   //TODO: add method to add UserEntityDetails to User with Id....... / Maybe works with update please test!!!
}
