package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import fhv.team11.project.ems.events.mapper.presentation.ActiveEventViewMapper;
import fhv.team11.project.ems.events.mapper.presentation.ActiveEventViewShallowDatabaseMapper;
import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.events.transfer.ActiveEventViewShallow;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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



    public List<ActiveEventViewShallow> getActiveEventViewsShallowForTemplateWithId(Long id) {
        return activeEventRepository.findAllByTemplateIdWithDates(id)
                .stream()
                .map(ActiveEventViewShallowDatabaseMapper.INSTANCE::getView)
                .toList();
    }

    @Transactional
    public ActiveEventView getActiveEventById(Long id) throws DomainValidationException {
        //TODO: direct mapper from database to view
        return ActiveEventViewMapper.INSTANCE.getView(eventDomainDatabaseFactory.getDomainById(id));
    }
}
