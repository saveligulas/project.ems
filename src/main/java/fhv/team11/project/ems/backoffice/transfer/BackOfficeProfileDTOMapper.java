package fhv.team11.project.ems.backoffice.transfer;

import fhv.team11.project.ems.backoffice.BackOfficeProfileEntity;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import org.jspecify.annotations.Nullable;

public class BackOfficeProfileDTOMapper implements IDTOEntityBiMapper<BackOfficeProfileEntity, BackOfficeProfileDTO> {
    //TODO: implement
    public static final BackOfficeProfileDTOMapper INSTANCE = new BackOfficeProfileDTOMapper();

    @Override
    public BackOfficeProfileEntity getEntity(BackOfficeProfileDTO dto) {
        return null;
    }

    @Override
    public @Nullable BackOfficeProfileDTO getDTO(BackOfficeProfileEntity entity) {
        return null;
    }
}
