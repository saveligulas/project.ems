package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ActiveEventModel;
import fhv.team11.project.ems.events.transfer.ActiveEventDTOContainer;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;

import javax.annotation.Nullable;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ActiveEventDTOMapper {

    public static final ActiveEventDTOMapper INSTANCE = new ActiveEventDTOMapper();

    public ActiveEventDTOContainer toDTO(ActiveEventModel activeEventModel,
                                         DomainValidatorFactory domainValidatorFactory) {
        ActiveEventWizardDTO activeEventWizardDTO = new ActiveEventWizardDTO(domainValidatorFactory);
        ActiveEventListDTO activeEventListDTO = new ActiveEventListDTO();

        activeEventWizardDTO.setScheduleEvent(ScheduleDTOMapper.INSTANCE.toDTO(activeEventModel.getScheduleEventModel()));
        activeEventWizardDTO.setActiveEventDates(activeEventModel.getActiveEventDates().stream()
                .map(ActiveEventDateDTOMapper.INSTANCE::toDTO)
                .collect(Collectors.toCollection(TreeSet::new)));

        activeEventListDTO.setId(activeEventModel.getId());

        return new ActiveEventDTOContainer(activeEventWizardDTO, activeEventListDTO);
    }

    //TODO: Handle mapping error? Maybe change it?
    public ActiveEventModel toModel(ActiveEventWizardDTO activeEventWizardDTO,
                                    @Nullable ActiveEventListDTO activeEventListDTO,
                                    @Nullable Long scheduleId) throws DomainFieldValidationException {
        return new ActiveEventModel(activeEventListDTO.getId(),activeEventWizardDTO.getActiveEventDates().stream()
                .map(date -> {
                    try {
                        return ActiveEventDateDTOMapper.INSTANCE.toModel(date);
                    } catch (DomainFieldValidationException e) {
                        throw new RuntimeException("Error mapping ActiveEventDateDTO to model", e);
                    }})
                .collect(Collectors.toCollection(TreeSet::new)),
                ScheduleDTOMapper.INSTANCE.toModel(activeEventWizardDTO.getScheduleEvent(),scheduleId));
    }
}
