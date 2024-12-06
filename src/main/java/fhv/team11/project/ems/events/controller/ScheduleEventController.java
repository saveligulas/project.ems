package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.events.error.ScheduleEventDTOValidationException;
import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ScheduleEventController {
    private final EventTemplateService eventTemplateService;
    private final ActiveEventWizardService activeEventWizardService;

    private final DomainValidatorFactory domainValidatorFactory;

    @Autowired
    public ScheduleEventController(EventTemplateService eventTemplateService, ActiveEventWizardService activeEventWizardService,DomainValidatorFactory domainValidatorFactory) {
        this.eventTemplateService = eventTemplateService;
        this.activeEventWizardService = activeEventWizardService;
        this.domainValidatorFactory = domainValidatorFactory;
    }

    @GetMapping("/event/manage/{id}/plan/appointments")
    public String planAppointments(@PathVariable("id") Long templateId,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
        //TODO catch errors in list that was given
        BindingResult bindingResult = domainValidatorFactory.getValidator(ActiveEventWizardDTO.class).validate(wizard);
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Validation Error: " + error.getDefaultMessage()); // Logging errors
            });
            redirectAttributes.addFlashAttribute("validationErrors", bindingResult.getAllErrors());
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        if (wizard == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired. Please start over.");
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        model.addAttribute("wizard", wizard);
        model.addAttribute("eventTemplate", eventTemplateService.getTemplateById(templateId));
        model.addAttribute("dateTime", new ScheduleEventDTO()); // Prepares the DTO
        EventTemplateListDTO listTemplate = eventTemplateService.getTemplateListByID(templateId);
        model.addAttribute("listTemplate", listTemplate);
        return "plan-appointments";
    }

    @PostMapping("/event/manage/{id}/plan/appointments")
    public String appointmentPlanned(@PathVariable("id") Long templateId,
                                     @Valid @ModelAttribute("dateTime") ScheduleEventDTO scheduleEventDTO,
                                     BindingResult result,
                                     Model model,
                                     HttpSession session) {
        model.addAttribute("templateId", templateId);

        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizard == null) {
            model.addAttribute("error", "Session expired. Please start over.");
            return "redirect:/event/manage/" + templateId + "/plan";
        }

        try {
            wizard.setScheduleEvent(scheduleEventDTO);
            session.setAttribute("wizard", wizard);
        } catch (ScheduleEventDTOValidationException ex) {
            ex.getBindingResult().getAllErrors().forEach(error -> {
                System.out.println("Validation Error: " + error.getDefaultMessage()); // Logging
                result.addError(error);
            });
        }

        if (result.hasErrors()) {
            model.addAttribute("wizard", wizard);
            model.addAttribute("eventTemplate", eventTemplateService.getTemplateById(templateId));
            model.addAttribute("listTemplate", eventTemplateService.getTemplateListByID(templateId));
            return "plan-appointments";
        }
        return "redirect:/event/manage/" + templateId + "/plan/appointments";
    }



    @PostMapping("/event/manage/{id}/plan/appointments/create")
    public String createEvent(@PathVariable("id") Long templateId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              SessionStatus sessionStatus) {
        ActiveEventWizardDTO wizardDTO = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizardDTO != null) {
            activeEventWizardService.createActiveEvent(wizardDTO, templateId);
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
