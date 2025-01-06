package fhv.team11.project.ems.events.mapper.presentation;

import com.beust.ah.A;
import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.AppointmentEntity;
import fhv.team11.project.ems.events.transfer.AppointmentDTO;

public class AppointmentDTODatabaseMapper implements IPresentationDatabaseMapper<AppointmentDTO, AppointmentEntity> {
    public static final AppointmentDTODatabaseMapper INSTANCE = new AppointmentDTODatabaseMapper();

    private AppointmentDTODatabaseMapper() {
    }

    @Override
    public AppointmentDTO getView(AppointmentEntity entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
