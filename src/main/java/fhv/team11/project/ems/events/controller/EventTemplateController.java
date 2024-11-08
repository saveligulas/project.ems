package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.service.EventTemplateService;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("/blueprint")
public class EventTemplateController {

    private final EventTemplateService eventTemplateService;

    @Autowired
    public EventTemplateController(EventTemplateService eventTemplateService) {
        this.eventTemplateService = eventTemplateService;
    }

    @GetMapping("/event")
    public ModelAndView createBlueprintPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-blueprint");
        modelAndView.addObject("eventTemplate", new EventTemplateDTO());
        modelAndView.addObject("categories", Arrays.stream(EventCategory.values()).toList());
        return modelAndView;
    }

    @PostMapping("/event/manage")
    public ModelAndView createBlueprint(@ModelAttribute("blueprint") EventTemplateDTO eventTemplateDTO,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // return new ModelAndView("redirect:/blueprint");
        }
        eventTemplateService.createNewBlueprint(eventTemplateDTO);
        return new ModelAndView("redirect:/event/manage");
    }

    @GetMapping("/event/manage")
    public ModelAndView viewBlueprints() {
        List<EventTemplateDTO> bps = eventTemplateService.getListOfBlueprints(50);
        ModelAndView model = new ModelAndView("event-organizer");
        model.addObject("blueprints", bps);
        return model;
    }
}
