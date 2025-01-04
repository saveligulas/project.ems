package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import fhv.team11.project.ems.events.mapper.presentation.ActiveEventViewDTOMapper;
import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
public class ActiveEventService {

    private final ActiveEventRepository activeEventRepository;
    private final EventDomainDatabaseFactory eventDomainDatabaseFactory;


    @Autowired
    public ActiveEventService(ActiveEventRepository activeEventRepository,
                              AppointmentRepository appointmentRepository,
                              ScheduleRepository scheduleRepository,
                              EventDateRepository eventDateRepository,
                              EventTemplateEntityRepository eventTemplateEntityRepository,
                              DomainValidatorFactory domainValidatorFactory, EventDomainDatabaseFactory eventDomainDatabaseFactory) {
        this.activeEventRepository = activeEventRepository;
        this.eventDomainDatabaseFactory = eventDomainDatabaseFactory;
    }



    public List<ActiveEventView> getAllActiveEvents() {
        //TODO add database direct mapper
        return null;
    }

    @Transactional
    public ActiveEventView getActiveEventById(Long id) throws DomainValidationException {
        //TODO: direct mapper from database to view
        return ActiveEventViewDTOMapper.INSTANCE.getView(eventDomainDatabaseFactory.getDomainById(id));
    }
}
