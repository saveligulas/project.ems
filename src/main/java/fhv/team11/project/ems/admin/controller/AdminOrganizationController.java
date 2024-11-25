package fhv.team11.project.ems.admin.controller;

import fhv.team11.project.ems.admin.service.OrganizationService;
import fhv.team11.project.ems.organization.transfer.OrganizationDTO;
import fhv.team11.project.ems.organization.transfer.OrganizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class AdminOrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public AdminOrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ModelAttribute("organization")
    public OrganizationDTO organizationDTO() {
        return new OrganizationDTO();
    }

    @GetMapping("/admin/organizations")
    public ModelAndView getOrganizationsView() {
        ModelAndView modelAndView = new ModelAndView("admin-organizations");
        modelAndView.addObject("organizations", organizationService.getOrganizations());
        modelAndView.addObject("organizationTypes", Arrays.asList(OrganizationType.values()));
        return modelAndView;
    }

    @PostMapping("/admin/organizations")
    public String addOrganization(@ModelAttribute("organization") OrganizationDTO organizationDTO) {
        organizationService.createNewOrganization(organizationDTO); // Save the new organization
        return "redirect:/admin/organizations"; // Redirect to the organizations page
    }
}
