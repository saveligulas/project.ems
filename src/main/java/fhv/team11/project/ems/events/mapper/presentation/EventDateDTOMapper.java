package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.commons.mapper.IPresentationDomainMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import fhv.team11.project.ems.events.transfer.EventScheduleDTO;
import org.jspecify.annotations.NullMarked;

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
                EventScheduleDTOMapper.INSTANCE.getDomain(presentationObject.getEventScheduleDTO()));
    }

    @Override
    public EventDateDTO getView(EventDate domain) {
        EventDateDTO dto = new EventDateDTO();
        dto.setDate(domain.getDate());
        dto.setName(domain.getName());
        dto.setEventScheduleDTO(EventScheduleDTOMapper.INSTANCE.getView(domain.getSchedule()));
        return dto;
    }
}
