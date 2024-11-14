package fhv.team11.project.ems.commons.controller.error.handler;

import fhv.team11.project.ems.commons.controller.error.validation.BindingResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements HandleRedirectException, HandleBindingResultException {

    @ExceptionHandler(BindingResultException.class)
    public String handleBindingResult(BindingResultException ex, RedirectAttributes redirectAttributes) {
        addBindingResultToRedirect(ex, redirectAttributes);
        log.error("Validation Exception occurred at: {} | BindingResult: {}", ex.getExceptionEndpoint(), ex.getBindingResult());
        return getRedirect(ex.getRedirectEndpoint());
    }
}
