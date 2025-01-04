package fhv.team11.project.ems.events.mapper.presentation;

import com.beust.ah.A;
import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.transfer.AppointmentDTO;

public class AppointmentDTOMapper implements IBiPresentationDomainMapper<AppointmentDTO, Appointment> {
    public static final AppointmentDTOMapper INSTANCE = new AppointmentDTOMapper();

    private AppointmentDTOMapper() {}

    @Override
    public AppointmentDTO getView(Appointment domain) {
        AppointmentDTO dto = new AppointmentDTO();

        dto.setStartTime(domain.getStartTime());
        dto.setEndTime(domain.getEndTime());
        dto.setTitle(domain.getTitle());
        dto.setDescription(domain.getDescription());

        return dto;
    }

    @Override
    public Appointment getDomain(AppointmentDTO presentationObject) throws DomainValidationException {
        return new Appointment(
                null,
                presentationObject.getStartTime(),
                presentationObject.getEndTime(),
                presentationObject.getTitle(),
                presentationObject.getDescription()
        );
    }
}
