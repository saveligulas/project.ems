package fhv.team11.project.ems.commons.controller.error.handler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface HandleRedirectException {
    default String getRedirect(String redirectEndpoint) {
        return "redirect:/" + redirectEndpoint;
    }
    default void addErrorMessagesToRedirect(List<String> errorMessages, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
    }
}
