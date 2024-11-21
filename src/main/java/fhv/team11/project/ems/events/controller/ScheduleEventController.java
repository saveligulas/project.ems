package fhv.team11.project.ems.events.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ScheduleEventController {
    private final EventTemplateService eventTemplateService;
    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public ScheduleEventController(EventTemplateService eventTemplateService, ActiveEventWizardService activeEventWizardService) {
        this.eventTemplateService = eventTemplateService;
        this.activeEventWizardService = activeEventWizardService;
    }

    @GetMapping("/event/manage/{id}/plan/appointments")
    public String planAppointments(@PathVariable("id") Long templateId,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
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
                                     RedirectAttributes redirectAttributes,
                                     HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("dateTime", scheduleEventDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dateTime", result);
            return "redirect:/event/manage/" + templateId + "/plan/appointments";
        }
        ActiveEventWizardDTO wizard = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizard == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired. Please start over.");
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        wizard.setScheduleEvent(scheduleEventDTO);
        session.setAttribute("wizard", wizard);
        return "redirect:/event/manage/" + templateId + "/plan/appointments";
    }

    @PostMapping("/event/manage/{id}/plan/appointments/create")
    public String createEvent(@PathVariable("id") Long templateId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        ActiveEventWizardDTO wizardDTO = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizardDTO != null) {
            // Wizard persisten
            //activeEventWizardService.save(wizardDTO);
            // Entferne wizard aus session
            session.removeAttribute("wizard");
            session.removeAttribute("listTemplate");
        } else {
            redirectAttributes.addFlashAttribute("error", "Session expired. Please start over.");
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        return "redirect:/event";
    }
}
