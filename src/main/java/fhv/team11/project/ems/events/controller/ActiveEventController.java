package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("wizard")
public class ActiveEventController {

    private final EventTemplateService eventTemplateService;
    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public ActiveEventController(ActiveEventWizardService activeEventWizardService, EventTemplateService eventTemplateService) {
        this.activeEventWizardService = activeEventWizardService;
        this.eventTemplateService = eventTemplateService;
    }

    @ModelAttribute("wizard")
    public ActiveEventWizardDTO addActiveEventWizardDTO() {
        return new ActiveEventWizardDTO();
    }

    @GetMapping("/event/manage/{id}/plan")
    public ModelAndView viewEventTemplate(@PathVariable("id") String templateId,@ModelAttribute("ListTemplate") EventTemplateListDTO eventTemplateListDTO){
        ModelAndView modelAndView = new ModelAndView("plan-event");
        modelAndView.addObject("ListTemplate",eventTemplateListDTO);
        return modelAndView;
    }

    @PostMapping("event/manage/{id}/plan")
    public String addEventDate(
            @ModelAttribute("eventDate") ActiveEventDateDTO eventDateDTO,
            ActiveEventWizardDTO activeEventWizardDTO,
            RedirectAttributes redirectAttributes) {
            return "";
    }

}
