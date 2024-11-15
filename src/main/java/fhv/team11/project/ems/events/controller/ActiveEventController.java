package fhv.team11.project.ems.events.controller;

import ch.qos.logback.core.model.Model;
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
    public ModelAndView viewEventTemplate(@PathVariable("id") String templateId, @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO/*,@ModelAttribute("ListTemplate") EventTemplateListDTO eventTemplateListDTO*/){
        ModelAndView modelAndView = new ModelAndView("plan-event");

        modelAndView.addObject("ListTemplate",eventTemplateService.getTemplateListByID(Long.valueOf(templateId)));
        modelAndView.addObject("eventDate", new ActiveEventDateDTO());
        modelAndView.addObject("wizard", wizardDTO);
        return modelAndView;
    }

    @PostMapping("event/manage/{id}/plan")
    public String addEventDate(
            @ModelAttribute("eventDate") ActiveEventDateDTO eventDateDTO,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
            @PathVariable("id") String templateId) {
        wizardDTO.addActiveEvent(eventDateDTO);
        //redirectAttributes.addAttribute("ListTemplate", eventTemplateService.getTemplateListByID(Long.valueOf(templateId)));
        //redirectAttributes.addAttribute("eventDate", eventDateDTO);
        //redirectAttributes.addAttribute("ListTemplate", eventTemplateService.getTemplateListByID(Long.valueOf(templateId)));
        return "redirect:/event/manage/{id}/plan";
    }

}
