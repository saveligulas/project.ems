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
import org.springframework.web.bind.annotation.*;

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
                                   Model model) {
        ActiveEventWizardDTO wizardDTO = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizardDTO == null) {
            return "redirect:/event/manage/" + templateId + "/plan";
        }
        model.addAttribute("wizard", wizardDTO);

        model.addAttribute("eventTemplate", eventTemplateService.getTemplateById(templateId));

        model.addAttribute("dateTime", new ScheduleEventDTO());

        EventTemplateListDTO listTemplate = (EventTemplateListDTO) session.getAttribute("listTemplate");
        if (listTemplate == null) {
            listTemplate = eventTemplateService.getTemplateListByID(templateId);
            session.setAttribute("listTemplate", listTemplate);
        }
        model.addAttribute("listTemplate", listTemplate);

        return "plan-appointments";
    }

    @PostMapping("/event/manage/{id}/plan/appointments")
    public String appointmentPlanned(@PathVariable("id") Long templateId,
                                     HttpSession session,
                                     @Valid @ModelAttribute("dateTime") ScheduleEventDTO scheduleEventDTO) {
        ActiveEventWizardDTO wizardDTO = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizardDTO == null) {
            wizardDTO = new ActiveEventWizardDTO();
            session.setAttribute("wizard", wizardDTO);
        }
        wizardDTO.setScheduleEvent(scheduleEventDTO);
        return "redirect:/event/manage/" + templateId + "/plan/appointments";
    }

    @PostMapping("/event/manage/{id}/plan/appointments/create")
    public String createEvent(HttpSession session) {
        ActiveEventWizardDTO wizardDTO = (ActiveEventWizardDTO) session.getAttribute("wizard");
        if (wizardDTO != null) {
            // Persistieren Sie den Wizard mithilfe des Services
            //activeEventWizardService.saveWizard(wizardDTO);
            // Session bereinigen, falls erforderlich
            session.removeAttribute("wizard");
            session.removeAttribute("listTemplate");
        }
        return "redirect:/event";
    }
}
