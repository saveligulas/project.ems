package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.stereotype.Component;

@Component
public abstract class DomainDatabaseFactory implements HandleBindingResultException, HandleRedirectException {
}


