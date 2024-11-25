package fhv.team11.project.ems.commons.controller;

import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public HomeController(ActiveEventWizardService activeEventWizardService) {
        this.activeEventWizardService = activeEventWizardService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");


        EventCategory[] categories = EventCategory.values();
        modelAndView.addObject("categories", categories);

        List<ActiveEventWizardDTO> activeEvents = activeEventWizardService.getAllActiveEvents();
        modelAndView.addObject("activeEvents", activeEvents);

        return modelAndView;
    }
}

