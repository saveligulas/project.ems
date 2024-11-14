package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.error.DuplicateActiveEventDateException;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@Service
public class ActiveEventWizardService {

    public void addDateToWizard(ActiveEventWizardDTO wizardDTO, ActiveEventDateDTO dateDTO) {
        if (!wizardDTO.getActiveEventDates().add(dateDTO)) {

        }

    }
}
