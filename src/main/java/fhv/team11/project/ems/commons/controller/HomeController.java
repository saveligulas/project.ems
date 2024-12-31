package fhv.team11.project.ems.commons.controller;

import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final EventWizardService eventWizardService;
    private final ActiveEventService activeEventService;

    @Autowired
    public HomeController(EventWizardService eventWizardService, ActiveEventService activeEventService) {
        this.eventWizardService = eventWizardService;
        this.activeEventService = activeEventService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/index")
    public String indexRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");


        EventCategory[] categories = EventCategory.values();
        modelAndView.addObject("categories", categories);

        List<ActiveEventView> activeEvents = activeEventService.getAllActiveEvents();
        modelAndView.addObject("activeEvents", activeEvents);

        return modelAndView;
    }
}

