package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("wizard")
public class ActiveEventController {

    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public ActiveEventController(ActiveEventWizardService activeEventWizardService) {
        this.activeEventWizardService = activeEventWizardService;
    }

    @ModelAttribute("wizard")
    public ActiveEventWizardDTO addActiveEventWizardDTO() {
        return new ActiveEventWizardDTO();
    }

    @GetMapping("/event/manage/{id}/plan")
    public ModelAndView viewEventTemplate(@PathVariable("id") String templateId) {
        return new ModelAndView("placeholder");
    }

    @PostMapping("event/manage/{id}/plan")
    public String addEventDate(
            @ModelAttribute("eventDate") ActiveEventDateDTO eventDateDTO,
            ActiveEventWizardDTO activeEventWizardDTO,
            RedirectAttributes redirectAttributes) {

    }

}
