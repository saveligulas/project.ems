package fhv.team11.project.ems.events.validation;

import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    @Test
    void testValidAppointmentConstruction() throws DomainValidationException {
        Appointment appointment = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Valid Title",
                "Valid Description"
        );

        assertEquals(1L, appointment.getId());
        assertEquals(LocalTime.of(9, 0), appointment.getStartTime());
        assertEquals(LocalTime.of(10, 0), appointment.getEndTime());
        assertEquals("Valid Title", appointment.getTitle());
        assertEquals("Valid Description", appointment.getDescription());
    }

    @Test
    void testInvalidTitle() {
        assertThrows(DomainValidationException.class, () ->
                new Appointment(
                        1L,
                        LocalTime.of(9, 0),
                        LocalTime.of(10, 0),
                        "Bad",
                        "Description"
                )
        );
    }

    @Test
    void testNullStartTime() {
        assertThrows(DomainValidationException.class, () ->
                new Appointment(
                        1L,
                        null,
                        LocalTime.of(10, 0),
                        "Valid Title",
                        "Description"
                )
        );
    }

    @Test
    void testInvalidTimeOrder() {
        assertThrows(DomainValidationException.class, () ->
                new Appointment(
                        1L,
                        LocalTime.of(10, 0),
                        LocalTime.of(9, 0),
                        "Valid Title",
                        "Description"
                )
        );
    }

    @Test
    void testAppointmentComparison() throws DomainValidationException {
        Appointment earlier = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Earlier Meeting",
                "Description"
        );

        Appointment later = new Appointment(
                2L,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                "Later Meeting",
                "Description"
        );

        assertTrue(earlier.compareTo(later) < 0);
        assertTrue(later.compareTo(earlier) > 0);
    }

    @Test
    void testAppointmentEquality() throws DomainValidationException {
        Appointment appointment1 = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Meeting 1",
                "Description"
        );

        Appointment appointment2 = new Appointment(
                2L,
                LocalTime.of(9, 30),
                LocalTime.of(10, 30),
                "Meeting 2",
                "Description"
        );

        assertTrue(appointment1.equals(appointment2));
    }

    @Test
    void testNonOverlappingAppointments() throws DomainValidationException {
        Appointment appointment1 = new Appointment(
                1L,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Meeting 1",
                "Description"
        );

        Appointment appointment2 = new Appointment(
                2L,
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                "Meeting 2",
                "Description"
        );

        assertFalse(appointment1.equals(appointment2));
    }
}
