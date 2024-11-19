package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ActiveEventWizardService {

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
                                    EventTemplateRepository eventTemplateRepository) {
        this.activeEventRepository = activeEventRepository;
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.eventDateRepository = eventDateRepository;
        this.eventTemplateRepository = eventTemplateRepository;
    }

    @Transactional
    public void createActiveEvent(ActiveEventWizardDTO activeEventWizardDTO) {

        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setEventTemplate(eventTemplateRepository.findById(activeEventWizardDTO.getTemplateId()).orElse(null));
        activeEventRepository.save(activeEvent);
        Schedule schedule = new Schedule();
        scheduleRepository.save(schedule);

        for(ActiveEventDateDTO AED: activeEventWizardDTO.getActiveEventDates()){
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
