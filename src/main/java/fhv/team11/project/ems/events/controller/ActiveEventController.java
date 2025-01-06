package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.controller.IHandleRowPresentation;
import fhv.team11.project.ems.commons.validation.ValidationExceptionToBindingResultFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.service.ActiveEventService;
import fhv.team11.project.ems.events.service.EventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ActiveEventController implements IHandleEventWizard, IHandleRowPresentation {

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

    @GetMapping("/event/{templateId}")
    public ModelAndView showEventTemplateDetails(@PathVariable("templateId") Long templateId) {
        ModelAndView modelAndView = new ModelAndView("event-template-with-events");
        EventTemplateView eventTemplateView = eventTemplateService.getEventTemplateViewById(templateId);
        List<ActiveEventListView> activeEventViewsShallow = activeEventService.getActiveEventListViewsForTemplateWithId(templateId);
        modelAndView.addObject("rows", listToRows(activeEventViewsShallow, 3, modelAndView));
        modelAndView.addObject("eventTemplateView", eventTemplateView);
        modelAndView.addObject("templateId", templateId);
        return modelAndView;
    }

    @GetMapping("/event/{templateId}/active/{id}")
    public ModelAndView showEventDetails(@PathVariable("id") Long id,
                                   @PathVariable("templateId") Long templateId) throws DomainValidationException {
        ModelAndView modelAndView = new ModelAndView("event-details");
        ActiveEventView activeEvent = activeEventService.getActiveEventById(id);
        List<EventDateDTO> dates = activeEvent.getEventDates();
        modelAndView.addObject("rows", listToRows(dates, 2, modelAndView));
        modelAndView.addObject("activeEvent", activeEvent);
        return modelAndView;
    }
}
