package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.Appointment;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import fhv.team11.project.ems.events.transfer.*;

import java.util.Set;
import java.util.TreeSet;

public class ActiveEventViewMapper {

    public static final ActiveEventViewMapper INSTANCE = new ActiveEventViewMapper();

    public ActiveEventView getDTO(ActiveEvent activeEvent, Set<EventDateEntity> eventDateEntity, Appointment appointment) {
        ActiveEventView dto = new ActiveEventView();
        EventScheduleDTO eventScheduleDTO = new EventScheduleDTO();
        //TODO: fix this
        //eventScheduleDTO.setEndTime(appointment.getEndTime());
        //eventScheduleDTO.setStartTime(appointment.getStartTime());

        dto.setScheduleEvent(eventScheduleDTO);
        dto.setTemplateId(activeEvent.getEventTemplate().getId());

        TreeSet<EventDateDTO> eventDateDTOS = new TreeSet<>();
        for(EventDateEntity ed : eventDateEntity) {
            EventDateDTO eventDateDTO = new EventDateDTO();
            eventDateDTO.setDate(ed.getDate());
            eventDateDTO.setName(ed.getName());
            eventDateDTOS.add(eventDateDTO);
        }
        EventTemplateListDTO eventTemplateListDTO = new EventTemplateListDTO();
        eventTemplateListDTO.setName(activeEvent.getEventTemplate().getName());
        eventTemplateListDTO.setId(activeEvent.getEventTemplate().getId());
        dto.setEventTemplateListDTO(eventTemplateListDTO);

        //TODO: fix
        //dto.setEventTemplateDTO(EventTemplateDTOMapper.INSTANCE.getDomain(activeEvent.getEventTemplate()));

        ActiveEventListDTO activeEventListDTO = new ActiveEventListDTO();
        activeEventListDTO.setId(activeEvent.getId());

        dto.setActiveEventListDTO(activeEventListDTO);
        dto.setActiveEventDates(eventDateDTOS);

        return dto;
    }
}
