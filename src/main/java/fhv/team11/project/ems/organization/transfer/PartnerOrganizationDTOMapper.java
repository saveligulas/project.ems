package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityMapper;
import fhv.team11.project.ems.organization.PartnerOrganization;
import org.springframework.security.core.parameters.P;

public class PartnerOrganizationDTOMapper implements IDTOEntityBiMapper<PartnerOrganization, OrganizationDTO> {
    public static final PartnerOrganizationDTOMapper INSTANCE = new PartnerOrganizationDTOMapper();

    @Override
    public PartnerOrganization getEntity(OrganizationDTO organizationDTO) {
        PartnerOrganization partnerOrganization = new PartnerOrganization();
        partnerOrganization.setName(organizationDTO.getName());
        partnerOrganization.setShortName(organizationDTO.getName().toUpperCase().replaceAll("\\s", "").substring(0, 3));
        return partnerOrganization;
    }

    @Override
    public OrganizationDTO getDTO(PartnerOrganization entity) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(entity.getName());
        return organizationDTO;
    }
}
