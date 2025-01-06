package fhv.team11.project.ems.commons.validation.domain;

import jakarta.validation.constraints.Null;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface IValidationException {
    Map<String, String> getFieldErrors();
    List<String> getErrorMessages();
}
