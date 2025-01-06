package fhv.team11.project.ems.events.service;


import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import fhv.team11.project.ems.events.EventTemplateDomainDatabaseFactory;
import fhv.team11.project.ems.events.mapper.presentation.AppointmentDTOMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventDateDTOMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventWizardMapper;
import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.AppointmentDTO;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import fhv.team11.project.ems.events.transfer.EventWizard;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Slf4j
public class EventWizardService {
    private final ActiveEventRepository activeEventRepository;
    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final EventDateRepository eventDateRepository;
    private final EventTemplateEntityRepository eventTemplateEntityRepository;
    private final EventDomainDatabaseFactory eventDomainDatabaseFactory;
    private final EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory;

    @Autowired
    public EventWizardService(ActiveEventRepository activeEventRepository,
                              AppointmentRepository appointmentRepository,
                              ScheduleRepository scheduleRepository,
                              EventDateRepository eventDateRepository,
                              EventTemplateEntityRepository eventTemplateEntityRepository, EventDomainDatabaseFactory eventDomainDatabaseFactory, EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory) {
        this.activeEventRepository = activeEventRepository;
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.eventDateRepository = eventDateRepository;
        this.eventTemplateEntityRepository = eventTemplateEntityRepository;
        this.eventDomainDatabaseFactory = eventDomainDatabaseFactory;
        this.eventTemplateDomainDatabaseFactory = eventTemplateDomainDatabaseFactory;
    }

    public void updateWizard(HttpSession session, EventWizard eventWizardDTO) {
        session.setAttribute("eventWizard", eventWizardDTO);
    }

    public void validateEventDate(EventDateDTO eventDateDTO) throws DomainValidationException {
        EventDateDTOMapper.INSTANCE.getDomain(eventDateDTO);
    }

    @Transactional
    public void createActiveEvent(EventWizard eventWizard, Long templateId) throws DomainValidationException {
        Event event = EventWizardMapper.INSTANCE.getDomain(eventWizard);
        event.setEventTemplate(eventTemplateDomainDatabaseFactory.getDomainById(templateId));
        eventDomainDatabaseFactory.persist(event);
    }

    public EventWizard addAppointmentToScheduleForDate(EventWizard wizard, Integer eventDateIndex, AppointmentDTO appointmentDTO) throws DomainValidationException {
        Event event = EventWizardMapper.INSTANCE.getDomain(wizard);
        Appointment appointment = AppointmentDTOMapper.INSTANCE.getDomain(appointmentDTO);
        event.getEventDates().get(eventDateIndex).getSchedule().addAppointment(appointment);
        return EventWizardMapper.INSTANCE.getView(event);
    }

    public EventWizard addEventDateToEvent(EventWizard wizard, EventDateDTO eventDateDTO) throws DomainValidationException {
        Event event = EventWizardMapper.INSTANCE.getDomain(wizard);
        event.addEventDate(EventDateDTOMapper.INSTANCE.getDomain(eventDateDTO));
        return EventWizardMapper.INSTANCE.getView(event);
    }
}
