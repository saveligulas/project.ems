package fhv.team11.project.ems.events.controller;

import fhv.team11.project.ems.events.repo.Category;
import fhv.team11.project.ems.events.service.BlueprintService;
import fhv.team11.project.ems.events.transfer.BlueprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/blueprint")
public class BlueprintController {

    private final BlueprintService blueprintService;

    @Autowired
    public BlueprintController(BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    @GetMapping
    public ModelAndView createBlueprintPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-blueprint");
        modelAndView.addObject("blueprint", new BlueprintDTO());
        modelAndView.addObject("categories", Arrays.stream(Category.values()).toList());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createBlueprint(@ModelAttribute("blueprint") BlueprintDTO blueprintDTO) {
        blueprintService.createNewBlueprint(blueprintDTO);
        return new ModelAndView("events");
    }
}
