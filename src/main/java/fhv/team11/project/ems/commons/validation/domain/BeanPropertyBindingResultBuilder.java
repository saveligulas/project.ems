package fhv.team11.project.ems.commons.validation.domain;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.Map;

public class BeanPropertyBindingResultBuilder {
    private final BeanPropertyBindingResult beanPropertyBindingResult;

    public BeanPropertyBindingResultBuilder(IModelAttribute modelAttribute) {
        this.beanPropertyBindingResult = new BeanPropertyBindingResult(modelAttribute, modelAttribute.getModelAttributeName());
    }

    public BeanPropertyBindingResultBuilder(BeanPropertyBindingResult beanPropertyBindingResult) {
        this.beanPropertyBindingResult = beanPropertyBindingResult;
    }

    public void addRejectValue(String field) {
        this.addRejectValue(field, "An error occurred while binding field: " + field);
    }

    public void addRejectValue(String field, String message) {
        beanPropertyBindingResult.rejectValue(field, "domain.error", message);
    }

    public void addRejectValues(Map<String, String> fieldMessages) {
        for (Map.Entry<String, String> entry : fieldMessages.entrySet()) {
            addRejectValue(entry.getKey(), entry.getValue());
        }
    }

    public BeanPropertyBindingResult build() {
        return beanPropertyBindingResult;
    }
}
