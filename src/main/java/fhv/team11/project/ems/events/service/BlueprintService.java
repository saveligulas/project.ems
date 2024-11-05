package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.error.BackEndError;
import fhv.team11.project.ems.commons.error.DatabaseException;
import fhv.team11.project.ems.events.repo.Blueprint;
import fhv.team11.project.ems.events.repo.BlueprintRepository;
import fhv.team11.project.ems.events.transfer.BlueprintDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlueprintService {

    private final BlueprintRepository blueprintRepository;

    @Autowired
    public BlueprintService(BlueprintRepository blueprintRepository) {
        this.blueprintRepository = blueprintRepository;
    }

    public void createNewBlueprint(BlueprintDTO blueprintDTO) {
        Blueprint blueprint = BlueprintDTOMapper.getBlueprint(blueprintDTO);
        blueprintRepository.persist(blueprint);
    }

}
