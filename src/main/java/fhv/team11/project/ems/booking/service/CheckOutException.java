package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

import java.util.List;

public class CheckOutException extends DomainValidationException {
    public CheckOutException(List<String> errorMessages) {
        super(errorMessages);
    }
}
