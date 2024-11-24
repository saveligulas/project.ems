package fhv.team11.project.ems.organization.event;

import fhv.team11.project.ems.organization.PartnerOrganization;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EventOrganization {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private PartnerOrganization partnerOrganization;
}
