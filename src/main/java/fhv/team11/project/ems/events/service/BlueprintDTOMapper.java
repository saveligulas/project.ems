package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.events.repo.Blueprint;
import fhv.team11.project.ems.events.transfer.BlueprintDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;

import java.math.BigDecimal;

public class BlueprintDTOMapper {

    public static Blueprint getBlueprint(BlueprintDTO dto) {
        Blueprint blueprint = new Blueprint();
        blueprint.setName(dto.getName());
        blueprint.setCategory(dto.getCategory());
        blueprint.setPrice(BigDecimal.valueOf(dto.getPrice()));
        blueprint.setMinParticipants(dto.getMinParticipants());
        blueprint.setMaxParticipants(dto.getMaxParticipants());
        blueprint.setOverbookingPlaces(2);
        blueprint.setAddress(AddressDTOMapper.INSTANCE.toEntity(dto.getAddress()));
        blueprint.setUser(JwtSecurityContextHolder.getUser());
        return blueprint;
    }

    public static BlueprintDTO getBlueprintDTO(Blueprint blueprint) {
        BlueprintDTO dto = new BlueprintDTO();
        dto.setName(blueprint.getName());
        dto.setCategory(blueprint.getCategory());
        dto.setAddress(AddressDTOMapper.INSTANCE.toDTO(blueprint.getAddress()));
        dto.setMaxParticipants(blueprint.getMaxParticipants());
        dto.setMinParticipants(blueprint.getMinParticipants());
        dto.setPrice(blueprint.getPrice().doubleValue());
        return dto;
    }
}
