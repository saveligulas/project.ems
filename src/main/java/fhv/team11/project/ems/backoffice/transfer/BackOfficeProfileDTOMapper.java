package fhv.team11.project.ems.backoffice.transfer;

import fhv.team11.project.ems.backoffice.BackOfficeProfileEntity;
import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import org.jspecify.annotations.Nullable;

public class BackOfficeProfileDTOMapper implements IBiPresentationDomainMapper<BackOfficeProfileEntity, BackOfficeProfileDTO> {
    //TODO: implement
    public static final BackOfficeProfileDTOMapper INSTANCE = new BackOfficeProfileDTOMapper();

    @Override
    public @Nullable BackOfficeProfileDTO getDomain(BackOfficeProfileEntity entity) {
        return null;
    }

    @Override
    public BackOfficeProfileEntity getView(BackOfficeProfileDTO domain) {
        return null;
    }
}
