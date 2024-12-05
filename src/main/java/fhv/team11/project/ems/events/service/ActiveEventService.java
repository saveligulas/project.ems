package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.*;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActiveEventService {

    private final ActiveEventRepository activeEventRepository;
    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final EventDateRepository eventDateRepository;
    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public ActiveEventService(ActiveEventRepository activeEventRepository,
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
    public List<ActiveEventView> getAllActiveEvents() {
        List<ActiveEventView> activeEventViews = new ArrayList<>();

        List<ActiveEvent> activeEventsList = activeEventRepository.findAllWithTemplatesAndDates();
        for(ActiveEvent activeEvent : activeEventsList) {
            activeEventViews.add(ActiveEventViewMapper.INSTANCE.getDTO(activeEvent,
                    activeEvent.getEventDate(),
                    activeEvent.getEventDate().iterator().next().getSchedule().getAppointments().iterator().next()));
        }
        return activeEventViews;
    }
    @Transactional
    public List<ActiveEventView> getAllActiveEvents1() {

        return activeEventRepository.findAllWithTemplatesAndDates().stream()
                .map(activeEvent -> ActiveEventViewMapper.INSTANCE.getDTO(
                        activeEvent,
                        activeEvent.getEventDate(),
                        activeEvent.getEventDate().iterator().next().getSchedule().getAppointments().iterator().next()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ActiveEventView getActiveEventById(Long id) {
        return activeEventRepository.findByIdWithTemplateAndDate(id).map(activeEvent -> ActiveEventViewMapper.INSTANCE.getDTO(
                        activeEvent,
                        activeEvent.getEventDate(),
                        activeEvent.getEventDate().iterator().next().getSchedule().getAppointments().iterator().next()))
                .orElseThrow(() -> new EntityNotFoundException("ActiveEvent not found with id: " + id));
    }
}
