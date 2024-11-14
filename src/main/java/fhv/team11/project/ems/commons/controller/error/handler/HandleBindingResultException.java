package fhv.team11.project.ems.commons.controller.error.handler;

import fhv.team11.project.ems.commons.controller.error.RedirectionException;
import fhv.team11.project.ems.commons.controller.error.validation.BindingResultException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface HandleBindingResultException extends HandleRedirectException {
    default String getBindingResultKey(String modelAttributeName) {
        return "org.springframework.validation.BindingResult." + modelAttributeName;
    }

    default void addBindingResultToRedirect(BindingResultException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(getBindingResultKey(ex.getModelAttributeName()), ex.getBindingResult());
    }

    default void addBindingResultToModel(BindingResultException ex, Model model) {
        model.addAttribute(getBindingResultKey(ex.getModelAttributeName()), ex.getBindingResult());
    }
}
