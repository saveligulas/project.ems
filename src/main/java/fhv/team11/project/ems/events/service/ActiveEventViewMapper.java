package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.Appointment;
import fhv.team11.project.ems.events.repo.EventDate;
import fhv.team11.project.ems.events.transfer.*;

import java.util.Set;
import java.util.TreeSet;

public class ActiveEventViewMapper {

    public static final ActiveEventViewMapper INSTANCE = new ActiveEventViewMapper();

    public ActiveEventViewDTO getDTO(ActiveEvent activeEvent, Set<EventDate> eventDate, Appointment appointment) {
        ActiveEventViewDTO dto = new ActiveEventViewDTO();
        ScheduleEventDTO scheduleEventDTO = new ScheduleEventDTO();
        scheduleEventDTO.setEndTime(appointment.getEndTime());
        scheduleEventDTO.setStartTime(appointment.getStartTime());

        dto.setScheduleEvent(scheduleEventDTO);
        dto.setTemplateId(activeEvent.getEventTemplate().getId());

        TreeSet<ActiveEventDateDTO> activeEventDateDTOS = new TreeSet<>();
        for(EventDate ed : eventDate) {
            ActiveEventDateDTO activeEventDateDTO = new ActiveEventDateDTO();
            activeEventDateDTO.setDate(ed.getDate());
            activeEventDateDTO.setName(ed.getName());
            activeEventDateDTOS.add(activeEventDateDTO);
        }
        EventTemplateListDTO eventTemplateListDTO = new EventTemplateListDTO();
        eventTemplateListDTO.setName(activeEvent.getEventTemplate().getName());
        eventTemplateListDTO.setId(activeEvent.getEventTemplate().getId());
        dto.setEventTemplateListDTO(eventTemplateListDTO);

        dto.setEventTemplateDTO(EventTemplateDTOMapper.INSTANCE.getDTO(activeEvent.getEventTemplate()));

        ActiveEventListDTO activeEventListDTO = new ActiveEventListDTO();
        activeEventListDTO.setId(activeEvent.getId());

        dto.setActiveEventListDTO(activeEventListDTO);
        dto.setActiveEventDates(activeEventDateDTOS);

        return dto;
    }
}
