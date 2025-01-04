package fhv.team11.project.ems.organization.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.Data;

@Data
public class OrganizationListDTO implements IModelAttribute {
    private Long id;
    private OrganizationType type;
    private String name;
    private String shortName;
}
