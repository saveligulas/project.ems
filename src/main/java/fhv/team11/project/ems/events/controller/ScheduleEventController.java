package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.service.ActiveEventWizardService;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ModelAttribute("wizard")
    public ActiveEventWizardDTO addActiveEventWizardDTO() {
        return new ActiveEventWizardDTO();
    }

    @GetMapping("event/manage/{id}/plan/appointments")
    public ModelAndView planAppointments(@PathVariable("id") String templateId,
                                         HttpSession httpSession,
                                         @ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO) {
        ModelAndView model = new ModelAndView("plan-appointments");
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
    public String appointmentPLand(@ModelAttribute("wizard") ActiveEventWizardDTO wizardDTO,
                                   @PathVariable("id") String templateId,
                                   @ModelAttribute("dateTime") ScheduleEventDTO scheduleEventDTO){
        wizardDTO.setScheduleEvent(scheduleEventDTO);
        return "redirect:/event/manage/{id}/plan/appointments";
    }
}
