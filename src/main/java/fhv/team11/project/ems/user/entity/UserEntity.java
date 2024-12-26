package fhv.team11.project.ems.user.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NullMarked
public class UserEntity {
    @Id
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserEntityDetails userEntityDetails;
}
