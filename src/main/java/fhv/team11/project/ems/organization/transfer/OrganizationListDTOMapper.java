package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.organization.PartnerOrganization;

public class OrganizationListDTOMapper {
    public static final OrganizationListDTOMapper INSTANCE = new OrganizationListDTOMapper();

    public OrganizationListDTO getDTOWithType(PartnerOrganization partnerOrganization, OrganizationType type) {
        OrganizationListDTO organizationDTO = this.getView(partnerOrganization);
        organizationDTO.setType(type);
        return organizationDTO;
    }

    public OrganizationListDTO getView(PartnerOrganization partnerOrganization) {
        OrganizationListDTO organizationListDTO = new OrganizationListDTO();
        organizationListDTO.setId(partnerOrganization.getId());
        organizationListDTO.setName(partnerOrganization.getName());
        organizationListDTO.setShortName(partnerOrganization.getShortName());
        return organizationListDTO;
    }
}
