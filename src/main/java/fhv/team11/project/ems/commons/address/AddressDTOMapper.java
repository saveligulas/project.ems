package fhv.team11.project.ems.commons.address;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressDTOMapper {
    AddressDTOMapper INSTANCE = Mappers.getMapper(AddressDTOMapper.class);

    AddressEntity toEntity(AddressDTO addressDTO);
    AddressDTO toDTO(AddressEntity addressEntity);
}
