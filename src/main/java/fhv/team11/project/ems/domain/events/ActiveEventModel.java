package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.commons.IndexValidator;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.TreeSet;

public class ActiveEventModel implements IDomainObject{

    @Nullable
    private transient HashMap<String, String> fieldErrors;

    @Nullable
    private Long id;

     @Setter
    private TreeSet<ActiveEventDateModel> activeEventDates;

     @Setter
    private TemplateModel templateModel;

     @Setter
    private ScheduleEventModel scheduleEventModel;

    public ActiveEventModel(Long id, TreeSet<ActiveEventDateModel> activeEventDates, TemplateModel templateModel, ScheduleEventModel scheduleEventModel) throws DomainFieldValidationException {
        fieldErrors = new HashMap<>();

        setId(id);
        setActiveEventDates(activeEventDates);
        setTemplateModel(templateModel);
        setScheduleEventModel(scheduleEventModel);
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.id = id;
    }

    @Override
    public void checkFieldErrors() throws DomainInstantiationException {
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            throw new DomainInstantiationException(fieldErrors);
        }
        fieldErrors = null;
    }

    @Override
    public boolean isInstantiated() {
        return fieldErrors == null;
    }
}
