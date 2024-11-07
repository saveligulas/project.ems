package fhv.team11.project.ems.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/view-user")
    public ModelAndView viewUser() {
        return new ModelAndView("view-user");
    }

    @GetMapping("/events")
    public ModelAndView viewEvents() {

        return new ModelAndView("events");
    }

    @GetMapping("/event-organizer")
    public ModelAndView viewEventorganizer() {

        return new ModelAndView("event-organizer");
    }

}
