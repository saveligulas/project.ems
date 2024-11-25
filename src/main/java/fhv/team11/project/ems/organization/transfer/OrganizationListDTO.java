package fhv.team11.project.ems.organization.transfer;

import lombok.Data;

@Data
public class OrganizationListDTO {
    private Long id;
    private OrganizationType type;
    private String name;
    private String shortName;
}
