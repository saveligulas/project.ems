package fhv.team11.project.ems.events.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

public class DuplicateActiveEventDateException extends RuntimeException {
    @Getter
    private final BindingResult bindingResult;

    public DuplicateActiveEventDateException(BindingResult bindingResult) {
        super("");
        this.bindingResult = bindingResult;
    }
}
