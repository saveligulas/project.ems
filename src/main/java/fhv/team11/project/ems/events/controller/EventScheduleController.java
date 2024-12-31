package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.validation.ValidationExceptionToBindingResultFactory;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.error.ScheduleEventDTOValidationException;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventScheduleController implements IHandleEventWizard {
    private final EventTemplateService eventTemplateService;
    private final EventWizardService eventWizardService;

    @Autowired
    public EventScheduleController(EventTemplateService eventTemplateService, EventWizardService eventWizardService, DomainValidatorFactory domainValidatorFactory) {
        this.eventTemplateService = eventTemplateService;
        this.eventWizardService = eventWizardService;
    }

    @ModelAttribute("appointment")
    public AppointmentDTO appointmentDTO() {
        return new AppointmentDTO();
    }

    @GetMapping("/event/manage/{id}/plan/{eventDateIndex}")
    public ModelAndView planAppointmentsForDate(
            @PathVariable("id") Long templateId,
            @PathVariable("eventDateIndex") Integer eventDateIndex,
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("plan-appointments");
        EventWizard wizard = getWizard(session);

        wizard.addToView(modelAndView);
        modelAndView.addObject("eventSchedule", wizard.getEventDates().get(eventDateIndex).getEventScheduleDTO());
        eventTemplateService.getTemplateListByID(templateId).addToView(modelAndView);
        modelAndView.addObject("eventDateIndex", eventDateIndex);
        modelAndView.addObject("eventDateTitle", wizard.getEventDates().get(eventDateIndex).getName());
        if (wizard.getEventDates().size() - 1 > eventDateIndex) {
            modelAndView.addObject("nextEventDateIndex", eventDateIndex + 1);
        }
        return modelAndView;
    }

    @PostMapping("/event/manage/{id}/plan/{eventDateIndex}/appointment")
    public String addAppointmentToScheduleForDate(
            @PathVariable("id") Long templateId,
            @PathVariable("eventDateIndex") Integer eventDateIndex,
            @ModelAttribute("appointment") AppointmentDTO appointmentDTO,
            HttpSession session) {
        EventWizard wizard = getWizard(session);
        try {
            updateWizard(session, eventWizardService.addAppointmentToScheduleForDate(wizard, eventDateIndex, appointmentDTO));
        } catch (DomainValidationException e) {
            ValidationExceptionToBindingResultFactory.handle(e, appointmentDTO, "event/manage/" + templateId + "/plan/" + eventDateIndex);
        }
        return "redirect:/event/manage/" + templateId + "/plan/" + eventDateIndex;
    }



    @PostMapping("/event/manage/{id}/plan/finish")
    public String createEvent(@PathVariable("id") Long templateId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              SessionStatus sessionStatus) {
        //TODO: handle session cleanup and cleanup if user leaves boundaries of wizard with interceptors
        EventWizard wizardDTO = (EventWizard) session.getAttribute("wizard");
        if (wizardDTO != null) {
            eventWizardService.createActiveEvent(wizardDTO, templateId);
            session.removeAttribute("wizard");
            session.removeAttribute("listTemplate");
            sessionStatus.setComplete();
        } else {
            redirectAttributes.addFlashAttribute("error", "Session expired. Please start over.");
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        return "redirect:/event";
    }
}
