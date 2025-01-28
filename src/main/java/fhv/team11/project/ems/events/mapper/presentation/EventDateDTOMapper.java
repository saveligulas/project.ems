package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;

@NullMarked
public class EventDateDTOMapper implements IBiPresentationDomainMapper<EventDateDTO, EventDate> {
    public static final EventDateDTOMapper INSTANCE = new EventDateDTOMapper();

    private EventDateDTOMapper() {
    }

    @Override
    public EventDate getDomain(EventDateDTO presentationObject) throws DomainValidationException {
        return new EventDate(
                null,
                presentationObject.getDate(),
                presentationObject.getName(),
                EventScheduleDTOMapper.INSTANCE.getDomain(presentationObject.getEventSchedule()),
                new ArrayList<>());
    }

    @Override
    public EventDateDTO getView(EventDate domain) {
        EventDateDTO dto = new EventDateDTO();
        dto.setDate(domain.getDate());
        dto.setName(domain.getName());
        dto.setEventSchedule(EventScheduleDTOMapper.INSTANCE.getView(domain.getSchedule()));
        return dto;
    }
}
