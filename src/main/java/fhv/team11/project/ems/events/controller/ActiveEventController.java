package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActiveEventController {

    private final EventTemplateService eventTemplateService;
    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public ActiveEventController(ActiveEventWizardService activeEventWizardService, EventTemplateService eventTemplateService) {
        this.activeEventWizardService = activeEventWizardService;
        this.eventTemplateService = eventTemplateService;
    }

    @GetMapping("/event/manage/{id}/plan")
    public ModelAndView viewEventTemplate(@PathVariable("id") Long templateId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("plan-event");
        EventTemplateListDTO eventTemplateListDTO = eventTemplateService.getTemplateListByID(templateId);
        modelAndView.addObject("listTemplate", eventTemplateListDTO);

        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizard == null) {
            wizard = new ActiveEventWizardDTO();
            session.setAttribute("wizard", wizard);
        }
        modelAndView.addObject("wizard", wizard);
        modelAndView.addObject("eventDate", new ActiveEventDateDTO());
        return modelAndView;
    }

    @PostMapping("/event/manage/{id}/plan")
    public String addEventDate(@Valid ActiveEventDateDTO activeEventDateDTO,
                               HttpSession session,
                               @PathVariable("id") Long templateId) {
        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizard == null) {
            wizard = new ActiveEventWizardDTO();
        }
        wizard.addActiveEvent(activeEventDateDTO);
        session.setAttribute("wizard", wizard);
        return "redirect:/event/manage/" + templateId + "/plan";
    }

    @GetMapping("/event/manage/{id}/plan/redirect")
    public String planAppointmentsRedirect(@PathVariable("id") Long templateId) {
        return "redirect:/event/manage/" + templateId + "/plan/appointments";
    }

    @GetMapping("/active-events")
    public String showAllEvents(Model model) {
        List<ActiveEventWizardDTO> activeEvents = activeEventWizardService.getAllActiveEvents();
        model.addAttribute("activeEvents", activeEvents);
        return "all-events";
    }

    @GetMapping("/active-events/{id}")
    public String showEventDetails(@PathVariable("id") Long id, Model model) {
        ActiveEvent activeEvent = activeEventWizardService.getActiveEventById(id);
        model.addAttribute("activeEvent", activeEvent);
        return "event-details";
    }


}
