package fhv.team11.project.ems.events.transfer;


import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Data
public class EventScheduleDTO implements IModelAttribute {
    private final List<AppointmentDTO> appointments = new ArrayList<>();

    public void addAppointment(AppointmentDTO appointment) {
        this.appointments.add(appointment);
    }
}
