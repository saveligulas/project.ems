package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.database.IDTOEntityMapper;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.organization.customer.CorporateCustomerOrganization;
import fhv.team11.project.ems.organization.event.EventOrganization;

public class OrganizationDTOMapper implements IDTOEntityMapper<EventOrganization, OrganizationDTO>, IModelAttribute {
    @Override
    public EventOrganization getEntity(OrganizationDTO organizationDTO) {
        return null;
    }
}
