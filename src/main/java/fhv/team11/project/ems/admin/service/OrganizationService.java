package fhv.team11.project.ems.admin.service;

import fhv.team11.project.ems.organization.PartnerOrganization;
import fhv.team11.project.ems.organization.customer.CorporateCustomerOrganization;
import fhv.team11.project.ems.organization.event.EventOrganization;
import fhv.team11.project.ems.organization.repo.OrganizationRepository;
import fhv.team11.project.ems.organization.transfer.OrganizationDTO;
import fhv.team11.project.ems.organization.transfer.OrganizationListDTO;
import fhv.team11.project.ems.organization.transfer.OrganizationListDTOMapper;
import fhv.team11.project.ems.organization.transfer.OrganizationType;
import fhv.team11.project.ems.organization.transfer.PartnerOrganizationDTOMapper;
import jakarta.mail.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<OrganizationListDTO> getOrganizations() {
        List<OrganizationListDTO> organizationListDTOS = new ArrayList<>();

        organizationListDTOS.addAll(
                organizationRepository.getEventOrganizations()
                        .stream()
                        .map(eventOrganization -> OrganizationListDTOMapper.INSTANCE.getDTOWithType(eventOrganization.getPartnerOrganization(), OrganizationType.EVENT))
                        .toList());

        organizationListDTOS.addAll(
                organizationRepository.getCorporateCustomerOrganizations()
                        .stream()
                        .map(corporateCustomerOrganization -> OrganizationListDTOMapper.INSTANCE.getDTOWithType(corporateCustomerOrganization.getPartnerOrganization(), OrganizationType.CUSTOMER))
                        .toList());

        return organizationListDTOS;
    }

    public void createNewOrganization(OrganizationDTO organizationDTO) {
        PartnerOrganization partnerOrganization = PartnerOrganizationDTOMapper.INSTANCE.getEntity(organizationDTO);

        switch (organizationDTO.getType()) {
            case EVENT:
                EventOrganization eventOrganization = new EventOrganization();
                eventOrganization.setPartnerOrganization(partnerOrganization);
                organizationRepository.saveEventOrganization(eventOrganization);
                break;
            case CUSTOMER:
                CorporateCustomerOrganization corporateCustomerOrganization = new CorporateCustomerOrganization();
                corporateCustomerOrganization.setPartnerOrganization(partnerOrganization);
                organizationRepository.saveCorporateCustomerOrganization(corporateCustomerOrganization);
                break;
            default:
                throw new IllegalArgumentException("Unexpected type: " + organizationDTO.getType());
        }
    }
}
