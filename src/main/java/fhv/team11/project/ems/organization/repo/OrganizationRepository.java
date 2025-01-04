package fhv.team11.project.ems.organization.repo;

import fhv.team11.project.ems.organization.PartnerOrganization;
import fhv.team11.project.ems.organization.customer.CorporateCustomerOrganization;
import fhv.team11.project.ems.organization.event.EventOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationRepository {

    private final EventOrganizationRepository eventOrganizationRepository;
    private final CorporateCustomerOrganizationRepository corporateCustomerOrganizationRepository;

    @Autowired
    public OrganizationRepository(EventOrganizationRepository eventOrganizationRepository, CorporateCustomerOrganizationRepository corporateCustomerOrganizationRepository) {
        this.eventOrganizationRepository = eventOrganizationRepository;
        this.corporateCustomerOrganizationRepository = corporateCustomerOrganizationRepository;
    }

    public void saveEventOrganization(EventOrganization eventOrganization) {
        eventOrganizationRepository.save(eventOrganization);
    }

    public void saveCorporateCustomerOrganization(CorporateCustomerOrganization corporateCustomerOrganization) {
        corporateCustomerOrganizationRepository.save(corporateCustomerOrganization);
    }

    public List<EventOrganization> getEventOrganizations() {
        return eventOrganizationRepository.findAll();
    }

    public List<CorporateCustomerOrganization> getCorporateCustomerOrganizations() {
        return corporateCustomerOrganizationRepository.findAll();
    }
}
