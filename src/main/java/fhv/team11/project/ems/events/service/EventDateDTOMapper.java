package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;

import fhv.team11.project.ems.events.repo.Appointment;
import fhv.team11.project.ems.events.repo.EventDate;
import fhv.team11.project.ems.events.repo.Schedule;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;

import java.util.HashSet;
import java.util.Set;

public class EventDateDTOMapper implements IDTOEntityBiMapper<EventDate, ActiveEventDateDTO> {

    public static final EventDateDTOMapper INSTANCE = new EventDateDTOMapper();

    private EventDateDTOMapper() {}

    public EventDate getEntity(ActiveEventDateDTO dto) {
        EventDate eventDate = new EventDate();
        eventDate.setDate(dto.getDate());
        eventDate.setName(dto.getName());
        return eventDate;
    }

    @Override
    public ActiveEventDateDTO getDTO(EventDate entity) {
        return null;
    }

}
