package fhv.team11.project.ems.events.transfer;

import lombok.Getter;

@Getter
public class ActiveEventDTOContainer {
    private ActiveEventWizardDTO wizardDTO;
    private ActiveEventListDTO listDTO;

    public ActiveEventDTOContainer(ActiveEventWizardDTO wizardDTO, ActiveEventListDTO listDTO) {
        this.wizardDTO = wizardDTO;
        this.listDTO = listDTO;
    }
}