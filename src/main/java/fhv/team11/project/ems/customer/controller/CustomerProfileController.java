package fhv.team11.project.ems.customer.controller;

import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.commons.validation.model.IModelAttributeName;
import fhv.team11.project.ems.customer.service.CustomerProfileService;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerProfileController implements HandleBindingResultException {

    private final CustomerProfileService customerProfileService;
    private static final String CUSTOMER_PROFILE_MODEL_ATTRIBUTE_NAME = "customerProfile";

    @Autowired
    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @ModelAttribute(CUSTOMER_PROFILE_MODEL_ATTRIBUTE_NAME)
    public CustomerProfileDTO getCustomerProfileDTO() {
        return new CustomerProfileDTO();
    }

    @GetMapping("profiles/customer")
    public ModelAndView viewAllCustomerProfiles() {
        ModelAndView modelAndView = new ModelAndView("bo/bo-customer-profile-list");
        modelAndView.addObject("customerProfiles", customerProfileService.getListOfCustomerProfiles(0, 25));
        return modelAndView;
    }

    @GetMapping("profiles/customer/create")
    public ModelAndView viewCustomerProfileCreatePage() {
        return new ModelAndView("bo/bo-customer-profile-create");
    }

    @PostMapping("profiles/customer/create")
    public String createCustomerProfile(@Valid @ModelAttribute(CUSTOMER_PROFILE_MODEL_ATTRIBUTE_NAME) CustomerProfileDTO customerProfileDTO,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            addBindingResultToRedirect(CUSTOMER_PROFILE_MODEL_ATTRIBUTE_NAME, bindingResult, redirectAttributes);
            return "redirect:/profiles/customer/create";
        }

        customerProfileService.createNewCustomerProfile(customerProfileDTO);
        return "redirect:/profiles/customer";
    }
}
