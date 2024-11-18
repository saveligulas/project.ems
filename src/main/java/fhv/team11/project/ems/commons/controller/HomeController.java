package fhv.team11.project.ems.commons.controller;

import fhv.team11.project.ems.events.repo.EventCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        EventCategory[] categories = EventCategory.values();
        modelAndView.addObject("categories", categories);
        return  modelAndView;

    }
}
