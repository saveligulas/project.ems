package fhv.team11.project.ems.organization.customer;

import fhv.team11.project.ems.organization.PartnerOrganization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CorporateCustomerOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organization_id")
    private PartnerOrganization partnerOrganization;
}
