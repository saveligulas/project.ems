package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.Data;
import org.thymeleaf.model.IModel;

import java.time.LocalTime;
import java.util.Objects;

@Data
public class AppointmentDTO implements IModelAttribute {
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String description;
}
