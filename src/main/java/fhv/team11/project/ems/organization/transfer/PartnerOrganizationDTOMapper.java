package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.organization.PartnerOrganization;

public class PartnerOrganizationDTOMapper {
    public static final PartnerOrganizationDTOMapper INSTANCE = new PartnerOrganizationDTOMapper();

    public PartnerOrganization getEntity(OrganizationDTO organizationDTO) {
        PartnerOrganization partnerOrganization = new PartnerOrganization();
        partnerOrganization.setName(organizationDTO.getName());
        partnerOrganization.setShortName(organizationDTO.getName().toUpperCase().replaceAll("\\s", "").substring(0, 3));
        return partnerOrganization;
    }

    public OrganizationDTO getDomain(PartnerOrganization entity) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(entity.getName());
        return organizationDTO;
    }
}
