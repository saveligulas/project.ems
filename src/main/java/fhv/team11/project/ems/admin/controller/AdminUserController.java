package fhv.team11.project.ems.admin.controller;

import com.google.zxing.qrcode.decoder.Mode;
import fhv.team11.project.ems.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/user/details")
    public ModelAndView getUserContextDetails() {
        ModelAndView modelAndView = new ModelAndView("user-details");
        modelAndView.addObject("user", null);
        return modelAndView;
    }

    @GetMapping("/user/details/{id}")
    private ModelAndView getUserDetails(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user-details");
        modelAndView.addObject("user", adminUserService.getUserDetails(id));
        return modelAndView;
    }

    @PostMapping("/users")
    private String createUser(@RequestParam("email") String email) {
        adminUserService.createUserWithSetPassword(email);
        return "redirect:/users";
    }
}