package fhv.team11.project.ems.events.controller;

import ch.qos.logback.core.model.Model;
import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@SessionAttributes({"wizard", "listTemplate"})
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
    public ModelAndView viewEventTemplate(@PathVariable("id") String templateId,
                                          @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
                                          HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("plan-event");

        if(httpSession.isNew()){
            modelAndView.addObject("listTemplate", eventTemplateService.getTemplateListByID(Long.valueOf(templateId)));
        }else {
            modelAndView.addObject("listTemplate",httpSession.getAttribute("listTemplate"));
        }

        modelAndView.addObject("eventDate", new ActiveEventDateDTO());
        return modelAndView;
    }

    @PostMapping("event/manage/{id}/plan")
    public String addEventDate(
            @ModelAttribute("eventDate") ActiveEventDateDTO activeEventDateDTO,
            @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
            @PathVariable("id") String templateId) {
        wizardDTO.addActiveEvent(activeEventDateDTO);
        return "redirect:/event/manage/{id}/plan";
    }

    @GetMapping("/event/manage/{id}/plan/redirect")
    public String planAppointmentsRedirect(HttpSession httpSession,
                                           @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
                                           @PathVariable("id") String templateId,
                                           @RequestParam("name") String name) {
        EventTemplateListDTO eventlist = new EventTemplateListDTO(Long.valueOf(templateId),name);
        httpSession.setAttribute("listTemplate", eventlist);
        httpSession.setAttribute("wizard", wizardDTO);
        return "redirect:/event/manage/{id}/plan/appointments";
    }

}
