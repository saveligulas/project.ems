package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class EventTemplateController {

    private final EventTemplateService eventTemplateService;

    @Autowired
    public EventTemplateController(EventTemplateService eventTemplateService) {
        this.eventTemplateService = eventTemplateService;
    }

    private ModelAndView getCreateTemplatePage() {
        ModelAndView modelAndView = new ModelAndView("create-eventTemplate");
        modelAndView.addObject("categories", Arrays.stream(EventCategory.values()).toList());
        return modelAndView;
    }

    @ModelAttribute("eventTemplate")
    public EventTemplateDTO eventTemplate() {
        return new EventTemplateDTO();
    }

    @GetMapping("/event/manage")
    public ModelAndView createBlueprintPage() {
        return getCreateTemplatePage();
    }

    @PostMapping("/event/create")
    public String createBlueprint(@Valid @ModelAttribute("eventTemplate") EventTemplateDTO eventTemplateDTO,
                                        BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventTemplate", eventTemplateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventTemplate", result);

            return "redirect:/event/manage";
        }
        eventTemplateService.createNewTemplate(eventTemplateDTO);
        return "redirect:/event";
    }



    @GetMapping("/event")
    public ModelAndView viewEventOrganizer() {
        List<EventTemplateListDTO> bps = eventTemplateService.getListOfTemplates(0, 25);
        ModelAndView model = new ModelAndView("event-organizer");
        model.addObject("templates", bps);
        return model;
    }

    @GetMapping("/event/manage/{id}")
    public ModelAndView viewEventTemplate(@PathVariable("id") String templateId, RedirectAttributes redirectAttributes){
        ModelAndView model = new ModelAndView("view-eventTemplate");
        model.addObject("Template", eventTemplateService.getTemplateById(Long.valueOf(templateId)));
        EventTemplateListDTO eventlist = eventTemplateService.getTemplateListByID(Long.valueOf(templateId));
        model.addObject("ListTemplate",eventlist);
        return model;
    }

    @GetMapping("/event/manage/{id}/redirect")
    public String listEventTemplates(HttpSession httpSession,
                                     @RequestParam("name") String name,
                                     @PathVariable("id") String templateId) {
        EventTemplateListDTO eventlist = new EventTemplateListDTO(Long.valueOf(templateId),name);
        httpSession.setAttribute("ListTemplate", eventlist);
        return "redirect:/event/manage/{id}/plan";
    }
}
