package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.events.error.EventTemplateDTOValidationException;
import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class EventTemplateController implements HandleBindingResultException {

    private final EventTemplateService eventTemplateService;

    @Autowired
    public EventTemplateController(EventTemplateService eventTemplateService) {
        this.eventTemplateService = eventTemplateService;
    }

    @ModelAttribute("eventTemplate")
    public EventTemplateDTO eventTemplateDTO() {
        return new EventTemplateDTO();
    }

    @GetMapping("/event/manage")
    public ModelAndView createTemplatePage() {
        ModelAndView modelAndView = new ModelAndView("eo/create-eventTemplate");
        modelAndView.addObject("categories", Arrays.asList(EventCategory.values()));
        return modelAndView;
    }

    @PostMapping("/event/create")
    public String createTemplate(@Valid @ModelAttribute("eventTemplate") EventTemplateDTO eventTemplateDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(eventTemplateDTO.getModelAttributeName(), eventTemplateDTO);
            redirectAttributes.addFlashAttribute(this.getBindingResultKey(eventTemplateDTO.getModelAttributeName()), bindingResult);
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
    public String planEventRedirect(@PathVariable("id") Long templateId,@RequestParam("name") String name, Model model, HttpSession session) {
        EventTemplateListDTO eventList = new EventTemplateListDTO(Long.valueOf(templateId),name);
        session.setAttribute("listTemplate",eventList);
        return "redirect:/event/manage/" + templateId + "/plan";
    }
}
