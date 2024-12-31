package fhv.team11.project.ems.events.service;


import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.events.EventSchedule;
import fhv.team11.project.ems.events.mapper.persistence.EventDateDomainMapper;
import fhv.team11.project.ems.events.mapper.presentation.AppointmentDTOMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventDateDTOMapper;
import fhv.team11.project.ems.events.mapper.presentation.EventScheduleDTOMapper;
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
    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public EventWizardService(ActiveEventRepository activeEventRepository,
                              AppointmentRepository appointmentRepository,
                              ScheduleRepository scheduleRepository,
                              EventDateRepository eventDateRepository,
                              EventTemplateRepository eventTemplateRepository) {
        this.activeEventRepository = activeEventRepository;
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.eventDateRepository = eventDateRepository;
        this.eventTemplateRepository = eventTemplateRepository;
    }

    public void updateWizard(HttpSession session, EventWizard eventWizardDTO) {
        session.setAttribute("eventWizard", eventWizardDTO);
    }

    public void validateEventDate(EventDateDTO eventDateDTO) throws DomainValidationException {
        EventDateDTOMapper.INSTANCE.getDomain(eventDateDTO);
    }

    @Transactional
    public void createActiveEvent(EventWizard eventWizard, Long templateId) {
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
