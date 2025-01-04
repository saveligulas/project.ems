package fhv.team11.project.ems.events.transfer;

import lombok.Getter;

@Getter
public class ActiveEventDTOContainer {
    private EventWizard wizardDTO;
    private ActiveEventListDTO listDTO;

    public ActiveEventDTOContainer(EventWizard wizardDTO, ActiveEventListDTO listDTO) {
        this.wizardDTO = wizardDTO;
        this.listDTO = listDTO;
    }
}