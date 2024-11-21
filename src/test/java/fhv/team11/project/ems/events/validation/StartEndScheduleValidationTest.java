package fhv.team11.project.ems.events.validation;


import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StartEndScheduleValidationTest {

    private StartEndScheduleValidation validator;

    @BeforeEach
    void setUp() {
        validator = new StartEndScheduleValidation();
        StartEndSchedule annotation = new StartEndSchedule() {
            @Override
            public String min() {
                return "startTime";
            }

            @Override
            public String max() {
                return "endTime";
            }

            @Override
            public String message() {
                return "Invalid schedule: start time must be before end time.";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return StartEndSchedule.class;
            }
        };
        validator.initialize(annotation);
    }

    @Test
    void testValidSchedule() {
        DummySchedule schedule = new DummySchedule(LocalTime.of(10, 0), LocalTime.of(11, 0));
        assertTrue(validator.isValid(schedule, null));
    }

    @Test
    void testInvalidSchedule() {
        DummySchedule schedule = new DummySchedule(LocalTime.of(11, 0), LocalTime.of(10, 0));
        assertFalse(validator.isValid(schedule, null));
    }

    @Test
    void testNullValues() {
        DummySchedule schedule = new DummySchedule(null, LocalTime.of(10, 0));
        assertTrue(validator.isValid(schedule, null));

        schedule = new DummySchedule(LocalTime.of(10, 0), null);
        assertTrue(validator.isValid(schedule, null));
    }

    @Test
    void testNonLocalTimeValues() {
        NonLocalTimeSchedule schedule = new NonLocalTimeSchedule("10:00", "11:00");
        assertFalse(validator.isValid(schedule, null));
    }

    static class DummySchedule {
        private LocalTime startTime;
        private LocalTime endTime;

        public DummySchedule(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }
    }

    static class NonLocalTimeSchedule {
        private String startTime;
        private String endTime;

        public NonLocalTimeSchedule(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}

