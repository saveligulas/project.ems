package fhv.team11.project.ems.admin.controller;

import fhv.team11.project.ems.admin.service.OrganizationService;
import fhv.team11.project.ems.organization.transfer.OrganizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminOrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public AdminOrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/admin/organizations")
    public ModelAndView getOrganizationsView() {
        ModelAndView modelAndView = new ModelAndView("admin-organizations");
        modelAndView.addObject("organizations", organizationService.getOrganizations());
        modelAndView.addObject("organizationTypes", OrganizationType.values());
        return modelAndView;
    }
}
