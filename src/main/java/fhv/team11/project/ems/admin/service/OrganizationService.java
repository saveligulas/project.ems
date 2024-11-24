package fhv.team11.project.ems.admin.service;

import fhv.team11.project.ems.organization.repo.OrganizationRepository;
import fhv.team11.project.ems.organization.transfer.OrganizationListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<OrganizationListDTO> getOrganizations() {
        return null;
    }
}
