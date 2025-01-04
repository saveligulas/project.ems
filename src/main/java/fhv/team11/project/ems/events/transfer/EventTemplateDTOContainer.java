package fhv.team11.project.ems.events.transfer;

import lombok.Getter;

@Getter
public class EventTemplateDTOContainer {
    private EventTemplateDTO templateDTO;
    private EventTemplateListDTO listDTO;

    public EventTemplateDTOContainer(EventTemplateDTO templateDTO, EventTemplateListDTO listDTO) {
        this.templateDTO = templateDTO;
        this.listDTO = listDTO;
    }
}
