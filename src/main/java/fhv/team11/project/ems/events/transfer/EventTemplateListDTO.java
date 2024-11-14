package fhv.team11.project.ems.events.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventTemplateListDTO {
    private Long id;
    private String name;
}
