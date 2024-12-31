package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.validation.ValidationExceptionToBindingResultFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.events.transfer.EventWizard;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class ActiveEventController implements IHandleEventWizard {

    private final EventTemplateService eventTemplateService;
    private final EventWizardService eventWizardService;
    private final ActiveEventService activeEventService;

    @Autowired
    public ActiveEventController(EventWizardService eventWizardService, EventTemplateService eventTemplateService, ActiveEventService activeEventService) {
        this.eventWizardService = eventWizardService;
        this.eventTemplateService = eventTemplateService;
        this.activeEventService = activeEventService;
    }

    @ModelAttribute("eventDate")
    public EventDateDTO eventDateDTO() {
        return new EventDateDTO();
    }

    @GetMapping("/event/manage/{id}/plan")
    public ModelAndView planEventDates(@PathVariable("id") Long templateId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("plan-event");
        //TODO: Move this to redis
        EventTemplateListDTO eventTemplateListDTO = eventTemplateService.getTemplateListByID(templateId);
        modelAndView.addObject("eventTemplateList", eventTemplateListDTO);

        EventWizard wizard = initOrGetWizard(session);
        modelAndView.addObject("wizard", wizard);
        return modelAndView;
    }

    @PostMapping("/event/manage/{id}/plan")
    public String addEventDate(@ModelAttribute("eventDate") EventDateDTO eventDateDTO,
                               HttpSession session,
                               @PathVariable("id") Long templateId,
                               BindingResult result,
                               Model model) {
        EventWizard wizard = getWizard(session);
        try {
            updateWizard(session, eventWizardService.addEventDateToEvent(wizard, eventDateDTO));
        } catch (DomainValidationException e) {
            ValidationExceptionToBindingResultFactory.handle(e, eventDateDTO, "event/manage/" + templateId + "/plan");
        }

        return "redirect:/event/manage/" + templateId + "/plan";
    }

    @GetMapping("/event/manage/{id}/plan/redirect")
    public String planAppointmentsRedirect(@PathVariable("id") Long templateId) {
        //TODO: validate if dates are not too far apart with domain
        return "redirect:/event/manage/" + templateId + "/plan/0";
    }

    @GetMapping("/active-events")
    public String showAllEvents(Model model) {
        List<ActiveEventView> activeEvents = activeEventService.getAllActiveEvents();
        model.addAttribute("activeEvents", activeEvents);
        return "all-events";
    }

    @GetMapping("/active-events/{id}")
    public String showEventDetails(@PathVariable("id") Long id, Model model) {
        ActiveEventView activeEvent = activeEventService.getActiveEventById(id);
        model.addAttribute("activeEvent", activeEvent);
        return "event-details";
    }
}
