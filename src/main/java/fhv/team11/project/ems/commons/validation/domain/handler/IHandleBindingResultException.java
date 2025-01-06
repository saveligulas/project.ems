package fhv.team11.project.ems.commons.validation.domain.handler;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IHandleBindingResultException extends HandleRedirectException {
    default String getBindingResultKey(String modelAttributeName) {
        return "org.springframework.validation.BindingResult." + modelAttributeName;
    }

    default void ifHasErrorsAddToRedirect(String modelAttributeName, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addBindingResultToRedirect(modelAttributeName, bindingResult, redirectAttributes);
        }
    }

    default void addBindingResultToRedirect(BindingResultException ex, RedirectAttributes redirectAttributes) {
        addBindingResultToRedirect(ex.getModelAttributeName(), ex.getBindingResult(), redirectAttributes);
    }

    default void addBindingResultToRedirect(String modelAttributeName, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(modelAttributeName, bindingResult.getTarget());
        redirectAttributes.addFlashAttribute(getBindingResultKey(modelAttributeName), bindingResult);
    }

    default void addBindingResultToModel(BindingResultException ex, Model model) {
        model.addAttribute(getBindingResultKey(ex.getModelAttributeName()), ex.getBindingResult());
    }
}
