package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.user.User;
import fhv.team11.project.ems.commons.user.UserEntity;
import fhv.team11.project.ems.events.repo.Blueprint;
import fhv.team11.project.ems.events.repo.BlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BlueprintService {

    private final BlueprintRepository blueprintRepository;

    @Autowired
    public BlueprintService(BlueprintRepository blueprintRepository) {
        this.blueprintRepository = blueprintRepository;
    }

    public void createNewBlueprint(BlueprintDTO blueprintDTO) {
        Blueprint blueprint = new Blueprint();
        // UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication();
        // blueprint.setUser(new User(userEntity.getId()));

        blueprintRepository.save(blueprint);
    }

}
