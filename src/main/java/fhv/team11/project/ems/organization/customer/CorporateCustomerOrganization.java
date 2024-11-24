package fhv.team11.project.ems.organization.customer;

import fhv.team11.project.ems.organization.PartnerOrganization;
import jakarta.persistence.*;

@Entity
public class CorporateCustomerOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private PartnerOrganization partnerOrganization;
}
