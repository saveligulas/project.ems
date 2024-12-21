package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ScheduleEventModel;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;

import javax.annotation.Nullable;

public class ScheduleDTOMapper {

    public static final ScheduleDTOMapper INSTANCE = new ScheduleDTOMapper();

    public ScheduleEventDTO toDTO(ScheduleEventModel scheduleEventModel) {
        ScheduleEventDTO scheduleEventDTO = new ScheduleEventDTO();

        scheduleEventDTO.setStartTime(scheduleEventModel.getStartTime());
        scheduleEventDTO.setEndTime(scheduleEventModel.getEndTime());

        return scheduleEventDTO;
    }

    public ScheduleEventModel toModel(ScheduleEventDTO scheduleEventDTO,@Nullable Long scheduleId) throws DomainFieldValidationException {
        return new ScheduleEventModel(scheduleId, scheduleEventDTO.getStartTime(), scheduleEventDTO.getEndTime());
    }
}
