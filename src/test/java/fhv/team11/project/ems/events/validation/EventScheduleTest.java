package fhv.team11.project.ems.events.validation;

import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventScheduleTest {
    private EventSchedule schedule;

    @BeforeEach
    void setUp() throws DomainValidationException {
        schedule = new EventSchedule();
    }

    @Test
    void testConstructorWithValidId() throws DomainValidationException {
        EventSchedule schedule = new EventSchedule(1L, new ArrayList<>());
        assertEquals(1L, schedule.getId());
    }

    @Test
    void testConstructorWithNullId() throws DomainValidationException {
        EventSchedule schedule = new EventSchedule(null, new ArrayList<>());
        assertNull(schedule.getId());
    }

    @Test
    void testConstructorWithInvalidId() {
        assertThrows(DomainValidationException.class, () ->
                new EventSchedule(-1L, new ArrayList<>())
        );
    }

    @Test
    void testAddUniqueAppointment() throws DomainValidationException {
        Appointment appointment = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Meeting",
                "Description"
        );

        schedule.addAppointment(appointment);
        assertEquals(1, schedule.getAppointments().size());
        assertTrue(schedule.getAppointments().contains(appointment));
    }

    @Test
    void testAddOverlappingAppointment() throws DomainValidationException {
        Appointment appointment1 = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Meeting 1",
                "Description 1"
        );

        Appointment appointment2 = new Appointment(
                2L,
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                "Meeting 2",
                "Description 2"
        );

        schedule.addAppointment(appointment1);
        assertThrows(DomainValidationException.class, () ->
                schedule.addAppointment(appointment2)
        );
    }

    @Test
    void testAppointmentsAreSorted() throws DomainValidationException {
        Appointment appointment1 = new Appointment(
                1L,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                "Later Meeting",
                "Description"
        );

        Appointment appointment2 = new Appointment(
                2L,
                LocalTime.of(9, 0),
                LocalTime.of(9, 30),
                "Earlier Meeting",
                "Description"
        );

        schedule.addAppointment(appointment1);
        schedule.addAppointment(appointment2);

        assertEquals(appointment2, schedule.getAppointments().get(0));
        assertEquals(appointment1, schedule.getAppointments().get(1));
    }
}
