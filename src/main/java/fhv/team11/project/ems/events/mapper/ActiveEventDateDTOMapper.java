package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ActiveEventDateModel;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;

import javax.annotation.Nullable;

public class ActiveEventDateDTOMapper {

    public static final ActiveEventDateDTOMapper INSTANCE = new ActiveEventDateDTOMapper();

    public ActiveEventDateDTO toDTO(ActiveEventDateModel activeEventDateModel) {
        ActiveEventDateDTO activeEventDateDTO = new ActiveEventDateDTO();

        activeEventDateDTO.setDate(activeEventDateModel.getDate());
        activeEventDateDTO.setName(activeEventDateModel.getName());

        return activeEventDateDTO;
    }

    public ActiveEventDateModel toModel(ActiveEventDateDTO activeEventDateDTO,@Nullable Long eventDateId) throws DomainFieldValidationException {
        return new ActiveEventDateModel(eventDateId, activeEventDateDTO.getDate(), activeEventDateDTO.getName());
    }

    public ActiveEventDateModel toModel(ActiveEventDateDTO activeEventDateDTO) throws DomainFieldValidationException {
        return new ActiveEventDateModel(null, activeEventDateDTO.getDate(), activeEventDateDTO.getName());
    }
}
