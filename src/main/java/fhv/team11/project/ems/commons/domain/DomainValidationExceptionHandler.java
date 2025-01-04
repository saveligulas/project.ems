package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.BeanPropertyBindingResultBuilder;
import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class DomainValidationExceptionHandler implements HandleRedirectException, HandleBindingResultException {

    @ExceptionHandler(DomainToBindingResultException.class)
    public String handleDomainValidationException(DomainToBindingResultException e, RedirectAttributes redirectAttributes) {
        BeanPropertyBindingResultBuilder bindingResultBuilder = new BeanPropertyBindingResultBuilder(e.getModelAttribute());
        bindingResultBuilder.addRejectValues(e.getValidationException().getFieldErrors());
        BindingResult bindingResult = bindingResultBuilder.build();
        addBindingResultToRedirect(e.getModelAttribute().getModelAttributeName(), bindingResult, redirectAttributes);
        log.error("Validation Exception occurred at: {} | BindingResult: {}", e.getRedirectEndpoint(), bindingResult);
        return getRedirect(e.getRedirectEndpoint());
    }
}
