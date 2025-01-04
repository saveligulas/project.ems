package fhv.team11.project.ems.commons.validation.domain.handler;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class BindingResultExceptionHandler implements HandleRedirectException, HandleBindingResultException {

    @ExceptionHandler(BindingResultException.class)
    public String handleBindingResult(fhv.team11.project.ems.commons.validation.domain.error.BindingResultException ex, RedirectAttributes redirectAttributes) {
        addBindingResultToRedirect(ex, redirectAttributes);
        addErrorMessagesToRedirect(ex.getErrorMessages(), redirectAttributes);
        log.error("Validation Exception occurred at: {} | BindingResult: {}", ex.getExceptionEndpoint(), ex.getBindingResult());
        return getRedirect(ex.getRedirectEndpoint());
    }
}
