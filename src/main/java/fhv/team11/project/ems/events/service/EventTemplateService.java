package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateRepository;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventTemplateService {

    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public EventTemplateService(EventTemplateRepository eventTemplateRepository) {
        this.eventTemplateRepository = eventTemplateRepository;
    }

    public void createNewBlueprint(EventTemplateDTO eventTemplateDTO) {
        EventTemplate eventTemplate = EventTemplateDTOMapper.INSTANCE.getEntity(eventTemplateDTO);
        eventTemplateRepository.persist(eventTemplate);
    }

    public List<EventTemplateDTO> getListOfBlueprints(int num) {
        List<EventTemplate> bps = eventTemplateRepository.listNumberOfBlueprints(num);
        List<EventTemplateDTO> bpDTOs = new ArrayList<EventTemplateDTO>();
        for(EventTemplate b:bps){
            bpDTOs.add(EventTemplateDTOMapper.INSTANCE.getDTO(b));
        }
        return bpDTOs;
    }
}
