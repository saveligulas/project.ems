package fhv.team11.project.ems.commons.validation;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ValidationUtil {
    public static <T extends IModelAttribute> void validateFieldPredicates(T target, BindingResult result, List<FieldValidationExpression<T>> fieldValidationExpressions) {
        for (FieldValidationExpression<T> fieldValidationExpression : fieldValidationExpressions) {
            String fieldName = fieldValidationExpression.fieldName();
            Predicate<T> validationPredicate = fieldValidationExpression.predicate();

            try {
                if (!validationPredicate.test(target)) {
                    result.rejectValue(fieldName, "domain.error", "Invalid value for " + fieldName);
                }
            } catch (Exception e) {
                result.rejectValue(fieldName, "domain.error", "Error evaluating validation for " + fieldName);
            }
        }
    }
}
