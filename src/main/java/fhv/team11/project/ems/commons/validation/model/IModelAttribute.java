package fhv.team11.project.ems.commons.validation.model;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface IModelAttribute extends IModelAttributeName {
    default void addToView(ModelAndView modelAndView) {
        modelAndView.addObject(this.getModelAttributeName(), this);
    }

    default void addToView(Model model) {
        model.addAttribute(this.getModelAttributeName(), this);
    }
}
