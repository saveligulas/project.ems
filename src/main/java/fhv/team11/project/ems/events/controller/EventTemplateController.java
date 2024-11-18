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

    private ModelAndView getCreateTemplatePage(EventTemplateDTO eventTemplateDTO) {
        ModelAndView modelAndView = new ModelAndView("create-eventTemplate");
        modelAndView.addObject("categories", Arrays.asList(EventCategory.values()));
        modelAndView.addObject("eventTemplate", eventTemplateDTO);
        return modelAndView;
    }

    @GetMapping("/event/manage")
    public ModelAndView createTemplatePage(@ModelAttribute("eventTemplate") EventTemplateDTO eventTemplateDTO) {
        return getCreateTemplatePage(eventTemplateDTO);
    }

    @PostMapping("/event/create")
    public String createTemplate(@Valid @ModelAttribute("eventTemplate") EventTemplateDTO eventTemplateDTO,
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
        List<EventTemplateListDTO> templates = eventTemplateService.getListOfTemplates(0, 25);
        ModelAndView model = new ModelAndView("event-organizer");
        model.addObject("templates", templates);
        return model;
    }

    @GetMapping("/event/manage/{id}")
    public ModelAndView viewEventTemplate(@PathVariable("id") Long templateId) {
        ModelAndView model = new ModelAndView("view-eventTemplate");
        model.addObject("Template", eventTemplateService.getTemplateById(templateId));
        EventTemplateListDTO eventList = eventTemplateService.getTemplateListByID(templateId);
        model.addObject("listTemplate", eventList);
        return model;
    }

    @GetMapping("/event/manage/{id}/redirect")
    public String planEventRedirect(HttpSession session,
                                    @RequestParam("name") String name,
                                    @PathVariable("id") Long templateId) {
        EventTemplateListDTO eventList = new EventTemplateListDTO(templateId, name);
        session.setAttribute("listTemplate", eventList);
        return "redirect:/event/manage/" + templateId + "/plan";
    }
}
