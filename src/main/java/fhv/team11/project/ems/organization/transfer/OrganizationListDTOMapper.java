package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.database.IEntityDTOMapper;
import fhv.team11.project.ems.organization.PartnerOrganization;

public class OrganizationListDTOMapper implements IEntityDTOMapper<PartnerOrganization, OrganizationListDTO> {
    public static final OrganizationListDTOMapper INSTANCE = new OrganizationListDTOMapper();

    public OrganizationListDTO getDTOWithType(PartnerOrganization partnerOrganization, OrganizationType type) {
        OrganizationListDTO organizationDTO = this.getDTO(partnerOrganization);
        organizationDTO.setType(type);
        return organizationDTO;
    }

    @Override
    public OrganizationListDTO getDTO(PartnerOrganization partnerOrganization) {
        OrganizationListDTO organizationListDTO = new OrganizationListDTO();
        organizationListDTO.setId(partnerOrganization.getId());
        organizationListDTO.setName(partnerOrganization.getName());
        organizationListDTO.setShortName(partnerOrganization.getShortName());
        return organizationListDTO;
    }
}
