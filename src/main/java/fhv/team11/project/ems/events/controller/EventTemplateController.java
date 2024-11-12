package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class EventTemplateController {

    private final EventTemplateService eventTemplateService;

    @Autowired
    public EventTemplateController(EventTemplateService eventTemplateService) {
        this.eventTemplateService = eventTemplateService;
    }

    private ModelAndView getCreateTemplatePage() {
        ModelAndView modelAndView = new ModelAndView("create-eventTemplate");
        modelAndView.addObject("eventTemplate", new EventTemplateDTO());
        modelAndView.addObject("categories", Arrays.stream(EventCategory.values()).toList());
        return modelAndView;
    }

    @GetMapping("/event/manage")
    public ModelAndView createBlueprintPage(RedirectAttributes redirectAttributes) {
        return getCreateTemplatePage();
    }

    @PostMapping("/event/create")
    public ModelAndView createBlueprint(@Valid @ModelAttribute("eventTemplate") EventTemplateDTO eventTemplateDTO,
                                        BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView errorModel = getCreateTemplatePage();

        if (result.hasErrors()) {
            errorModel.addObject("eventTemplate", eventTemplateDTO);
            errorModel.addObject("error", result);

            return errorModel;
        }
        eventTemplateService.createNewBlueprint(eventTemplateDTO);
        return new ModelAndView("redirect:/event");
    }



    @GetMapping("/event")
    public ModelAndView viewEventOrganizer() {
        List<EventTemplateDTO> bps = eventTemplateService.getListOfBlueprints(0, 25);
        ModelAndView model = new ModelAndView("event-organizer");
        model.addObject("templates", bps);
        return model;
    }

    @GetMapping("/event/manage/{id}")
    public ModelAndView vgiewEventTemplate(@PathVariable("id") String TemplateName){
        ModelAndView model = new ModelAndView("view-eventTemplate");
        model.addObject("Template", eventTemplateService.getTemplateByName(TemplateName));
        return model;
    }
}
