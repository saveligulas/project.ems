package fhv.team11.project.ems.customer.controller;

import fhv.team11.project.ems.customer.service.CustomerProfileService;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileSearchController {

    private final CustomerProfileService customerProfileService;

    @Autowired
    public CustomerProfileSearchController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @GetMapping("/search")
    public List<CustomerProfileDTO> search(@RequestParam("query") String query) {
        List<CustomerProfileDTO> list = customerProfileService.searchCustomers(query, 0, 10).getContent();
        return list;
    }
}
