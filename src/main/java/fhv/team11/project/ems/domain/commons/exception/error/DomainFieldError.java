package fhv.team11.project.ems.domain.commons.exception.error;

public class DomainFieldError {
    final String fieldName;
    final String errorMessage;

    public DomainFieldError(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }
}
