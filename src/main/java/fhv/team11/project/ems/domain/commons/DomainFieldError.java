package fhv.team11.project.ems.domain.commons;

import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@NullMarked
@Getter
public class DomainFieldError {
    private final String fieldName;
    private final String message;
    private final List<String> responsibleFieldNames;

    public DomainFieldError(String fieldName, String message, @Nullable List<String> responsibleFieldNames) {
        this.fieldName = fieldName;
        this.message = message;

        if (responsibleFieldNames == null) {
            this.responsibleFieldNames = Collections.EMPTY_LIST;
        } else {
            this.responsibleFieldNames = Collections.unmodifiableList(responsibleFieldNames);
        }
    }
}
