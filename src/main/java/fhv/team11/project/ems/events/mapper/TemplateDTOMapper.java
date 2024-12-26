package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.TemplateModel;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateDTOContainer;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;

import javax.annotation.Nullable;

public class TemplateDTOMapper {

    public static final TemplateDTOMapper INSTANCE = new TemplateDTOMapper();

    public EventTemplateDTOContainer toDTO(TemplateModel eventTemplate) {
        EventTemplateDTO dto = new EventTemplateDTO();
        EventTemplateListDTO eventTemplateListDTO = new EventTemplateListDTO();

        dto.setName(eventTemplate.getName());
        dto.setCategory(eventTemplate.getCategory());
        dto.setPrice(eventTemplate.getPrice());
        dto.setAddress(AddressDTOMapper.INSTANCE.toDTO(eventTemplate.getAddress()));
        dto.setMinParticipants(eventTemplate.getMinParticipants());
        dto.setMaxParticipants(eventTemplate.getMaxParticipants());

        eventTemplateListDTO.setId(eventTemplate.getId());

        return new EventTemplateDTOContainer(dto,eventTemplateListDTO);
    }

    //TODO: Change DTO and mapper for overbook places if needed
    public TemplateModel toModel(EventTemplateDTO eventTemplateDTO,@Nullable EventTemplateListDTO eventTemplateListDTO, @Nullable Long addressId) throws DomainFieldValidationException {
        return new TemplateModel(eventTemplateListDTO.getId(), eventTemplateDTO.getName(), eventTemplateDTO.getCategory(), eventTemplateDTO.getPrice(),
                eventTemplateDTO.getMaxParticipants(), eventTemplateDTO.getMinParticipants(), AddressDTOMapper.INSTANCE.toModel(eventTemplateDTO.getAddress(),addressId), null);
    }
}
