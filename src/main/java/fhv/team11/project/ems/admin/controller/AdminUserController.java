package fhv.team11.project.ems.admin.controller;

import fhv.team11.project.ems.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", adminUserService.getListOfUsers(0, 25));
        return modelAndView;
    }

    @GetMapping("/user/details/{id}")
    private ModelAndView getUserDetails(@PathVariable("id") String id) {
        Long userId = -1L;
        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new ModelAndView("user-not-found");
        }
        ModelAndView modelAndView = new ModelAndView("user-details");
        modelAndView.addObject("user", adminUserService.getUserDetails(userId));
        return modelAndView;
    }
}
