package fhv.team11.project.ems.events.transfer;

import lombok.Data;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashSet;
import java.util.Set;

@Data
public class ActiveEventWizardDTO {
    private Set<ActiveEventDateDTO> activeEventDates = new HashSet<>();
}
