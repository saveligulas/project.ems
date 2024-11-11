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

@Controller
public class EventTemplateController {

    private final EventTemplateService eventTemplateService;

    @Autowired
    public EventTemplateController(EventTemplateService eventTemplateService) {
        this.eventTemplateService = eventTemplateService;
    }

    @GetMapping("/create-eventTemplate")
    public ModelAndView createBlueprintPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-eventTemplate");
        modelAndView.addObject("blueprint", new EventTemplateDTO());
        modelAndView.addObject("categories", Arrays.stream(EventCategory.values()).toList());
        return modelAndView;
    }

    @PostMapping("/event/manage")
    public ModelAndView createBlueprint(@Valid @ModelAttribute("blueprint") EventTemplateDTO eventTemplateDTO,
                                        BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("blueprint", eventTemplateDTO);
            redirectAttributes.addFlashAttribute("error", result);

            return new ModelAndView("redirect:/blueprint/create-eventTemplate");
        }
        eventTemplateService.createNewBlueprint(eventTemplateDTO);
        return new ModelAndView("redirect:/event-organizer");
    }



    @GetMapping("/event-organizer")
    public ModelAndView viewEventOrganizer() {
        List<EventTemplateDTO> bps = eventTemplateService.getListOfBlueprints(0, 25);
        ModelAndView model = new ModelAndView("event-organizer");
        model.addObject("blueprints", bps);
        return model;
    }

    @GetMapping("/view-eventTemplate")
    public ModelAndView viewEventTemplate(@RequestParam("name") String TemplateName){
        ModelAndView model = new ModelAndView("view-eventTemplate");
        model.addObject("Template",eventTemplateService.getTemplateByName(TemplateName));
        return model;
    }
}
