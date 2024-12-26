package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.commons.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.IDomainObject;
import fhv.team11.project.ems.domain.commons.IndexValidator;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.TreeSet;

@Getter
public class ActiveEventModel implements IDomainObject {

    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

     @Setter
    private TreeSet<ActiveEventDateModel> activeEventDates;


     @Setter
    private ScheduleEventModel scheduleEventModel;

    public ActiveEventModel(Long id, TreeSet<ActiveEventDateModel> activeEventDates, ScheduleEventModel scheduleEventModel) throws DomainFieldValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setActiveEventDates(activeEventDates);
        setScheduleEventModel(scheduleEventModel);
        this.constructorHelper.finish();
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            handleError(fieldName,errorMessage,constructorHelper);

        }

        this.id = id;
    }

}
