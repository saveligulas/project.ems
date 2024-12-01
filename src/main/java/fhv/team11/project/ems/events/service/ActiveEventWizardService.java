package fhv.team11.project.ems.events.service;


import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;


@Service
@Validated
@Slf4j
public class ActiveEventWizardService {
    private final DomainValidatorFactory domainValidatorFactory;

    private final ActiveEventRepository activeEventRepository;
    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final EventDateRepository eventDateRepository;
    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public ActiveEventWizardService(ActiveEventRepository activeEventRepository,
                                    AppointmentRepository appointmentRepository,
                                    ScheduleRepository scheduleRepository,
                                    EventDateRepository eventDateRepository,
                                    EventTemplateRepository eventTemplateRepository,
                                    DomainValidatorFactory domainValidatorFactory) {
        this.domainValidatorFactory = domainValidatorFactory;
        this.activeEventRepository = activeEventRepository;
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.eventDateRepository = eventDateRepository;
        this.eventTemplateRepository = eventTemplateRepository;
    }

    @Transactional
    public void createActiveEvent(ActiveEventWizardDTO activeEventWizardDTO, Long templateId) {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setEventTemplate(
                eventTemplateRepository.findById(templateId)
                        .orElseThrow(() -> new IllegalArgumentException("Template not found with ID: " + templateId))
        );
        activeEventRepository.save(activeEvent);
        Schedule schedule = new Schedule();
        scheduleRepository.save(schedule);

        for (ActiveEventDateDTO AED : activeEventWizardDTO.getActiveEventDates()) {
            EventDate eventDate = EventDateDTOMapper.INSTANCE.getEntity(AED);
            eventDate.setActiveEvent(activeEvent);
            eventDate.setSchedule(schedule);
            eventDateRepository.save(eventDate);
        }
        Appointment appointment = AppointmentDTOMapper.INSTANCE.getEntity(activeEventWizardDTO);
        appointment.setSchedule(schedule);
        appointmentRepository.save(appointment);
    }




}
