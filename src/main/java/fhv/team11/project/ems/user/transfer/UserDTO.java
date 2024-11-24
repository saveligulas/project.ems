package fhv.team11.project.ems.user.transfer;

import fhv.team11.project.ems.user.profile.AdministratorProfile;
import fhv.team11.project.ems.user.profile.EventOrganizerProfile;
import lombok.Data;

import java.util.Optional;

@Data
public class UserDTO {
    private String username;
    private Optional<CustomerProfileDTO> customerProfileDTO;
    private Optional<EventOrganizerProfileDTO> eventOrganizerProfile;
    private Optional<AdministratorProfileDTO> administratorProfile;
    private Optional<BackOfficeEmployeeProfileDTO> backOfficeEmployeeProfileDTO;
}
