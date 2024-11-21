package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@SessionAttributes({"wizard", "listTemplate"})
public class ScheduleEventController {
    private final EventTemplateService eventTemplateService;
    private final ActiveEventWizardService activeEventWizardService;

    @Autowired
    public ScheduleEventController(EventTemplateService eventTemplateService, ActiveEventWizardService activeEventWizardService) {
        this.eventTemplateService = eventTemplateService;
        this.activeEventWizardService = activeEventWizardService;
    }

    @GetMapping("event/manage/{id}/plan/appointments")
    public ModelAndView planAppointments(@PathVariable("id") String templateId,
                                         HttpSession httpSession,
                                         @ModelAttribute("EventDate")ActiveEventDateDTO activeEventDateDTO,
                                         @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO) {
        ModelAndView model = new ModelAndView("plan-appointments");
        ActiveEventWizardDTO activeEventWizard= (ActiveEventWizardDTO) httpSession.getAttribute("wizard");
        model.addObject("wizard",activeEventWizard);
        model.addObject("eventTemplate",eventTemplateService.getTemplateById(Long.valueOf(templateId)));
        model.addObject("dateTime", new ScheduleEventDTO());

        if(wizardDTO==null){
            //activeEventWizardService.getwizardDTOById(templateId);
            return new ModelAndView("redirect:/event/manage/plan");
        }

        if(httpSession.isNew()){
            model.addObject("listTemplate", eventTemplateService.getTemplateListByID(Long.valueOf(templateId)));
        }else {
            model.addObject("listTemplate",httpSession.getAttribute("listTemplate"));
        }


        return model;
    }

    @PostMapping("/event/manage/{id}/plan/appointments")
    public String appointmentPlanned(@ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
                                   @PathVariable("id") String templateId,
                                   @ModelAttribute("dateTime") ScheduleEventDTO scheduleEventDTO){
        wizardDTO.setScheduleEvent(scheduleEventDTO);
        return "redirect:/event/manage/{id}/plan/appointments";
    }
    @PostMapping("/event/manage/{id}/plan/appointments/create")
    public String createEvent(Model model,@ModelAttribute("wizard") ActiveEventWizardDTO activeEventWizardDTO){
        //service and persist wizard
        return ("redirect:/event");
    }
}
