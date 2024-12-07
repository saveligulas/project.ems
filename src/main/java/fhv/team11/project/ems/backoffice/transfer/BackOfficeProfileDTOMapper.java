package fhv.team11.project.ems.backoffice.transfer;

import fhv.team11.project.ems.backoffice.BackOfficeProfile;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import org.jspecify.annotations.Nullable;

public class BackOfficeProfileDTOMapper implements IDTOEntityBiMapper<BackOfficeProfile, BackOfficeProfileDTO> {
    //TODO: implement
    public static final BackOfficeProfileDTOMapper INSTANCE = new BackOfficeProfileDTOMapper();

    @Override
    public BackOfficeProfile getEntity(BackOfficeProfileDTO dto) {
        return null;
    }

    @Override
    public @Nullable BackOfficeProfileDTO getDTO(BackOfficeProfile entity) {
        return null;
    }
}
