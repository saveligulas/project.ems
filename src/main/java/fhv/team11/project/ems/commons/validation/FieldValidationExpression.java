package fhv.team11.project.ems.commons.validation;

import java.util.function.Predicate;

public record FieldValidationExpression<T>(String fieldName, Predicate<T> predicate, String message) {
}
