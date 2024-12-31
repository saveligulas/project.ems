package fhv.team11.project.ems.domain.commons;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeValidator {

    public static boolean isFutureDate(LocalDate date) {
        if (Validator.isNull(date)) return false;
        return date.isAfter(LocalDate.now());
    }

    public static boolean isFutureDateTime(LocalDateTime dateTime) {
        if (Validator.isNull(dateTime)) return false;
        return dateTime.isAfter(LocalDateTime.now());
    }

    public static boolean isFutureTime(LocalTime time) {
        if (Validator.isNull(time)) return false;
        return time.isAfter(LocalTime.now());
    }

    public static boolean isPastDate(LocalDate date) {
        if (Validator.isNull(date)) return false;
        return date.isBefore(LocalDate.now());
    }

    public static boolean isPastDateTime(LocalDateTime dateTime) {
        if (Validator.isNull(dateTime)) return false;
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static boolean isPastTime(LocalTime time) {
        if (Validator.isNull(time)) return false;
        return time.isBefore(LocalTime.now());
    }

    public static boolean datesOverlap(LocalDate start1, LocalDate end1,
                                       LocalDate start2, LocalDate end2) {
        if (Validator.isNull(start1) || Validator.isNull(end1) ||
                Validator.isNull(start2) || Validator.isNull(end2)) {
            return false;
        }

        return !end1.isBefore(start2) && !end2.isBefore(start1);
    }

    public static boolean dateTimesOverlap(LocalDateTime start1, LocalDateTime end1,
                                           LocalDateTime start2, LocalDateTime end2) {
        if (Validator.isNull(start1) || Validator.isNull(end1) ||
                Validator.isNull(start2) || Validator.isNull(end2)) {
            return false;
        }

        return !end1.isBefore(start2) && !end2.isBefore(start1);
    }

    public static boolean timesOverlap(LocalTime start1, LocalTime end1,
                                       LocalTime start2, LocalTime end2) {
        if (Validator.isNull(start1) || Validator.isNull(end1) ||
                Validator.isNull(start2) || Validator.isNull(end2)) {
            return false;
        }

        return !end1.isBefore(start2) && !end2.isBefore(start1);
    }

    public static boolean timeSpansOverlap(Duration span1Start, Duration span1Duration,
                                           Duration span2Start, Duration span2Duration) {
        if (Validator.isNull(span1Start) || Validator.isNull(span1Duration) ||
                Validator.isNull(span2Start) || Validator.isNull(span2Duration)) {
            return false;
        }

        long span1End = span1Start.toMillis() + span1Duration.toMillis();
        long span2End = span2Start.toMillis() + span2Duration.toMillis();

        return span1End > span2Start.toMillis() && span2End > span1Start.toMillis();
    }

    public static boolean isWithinRange(LocalDateTime dateTime,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd) {
        if (Validator.isNull(dateTime) || Validator.isNull(rangeStart) ||
                Validator.isNull(rangeEnd)) {
            return false;
        }

        return !dateTime.isBefore(rangeStart) && !dateTime.isAfter(rangeEnd);
    }

    public static boolean isWithinRange(LocalDate date,
                                        LocalDate rangeStart,
                                        LocalDate rangeEnd) {
        if (Validator.isNull(date) || Validator.isNull(rangeStart) ||
                Validator.isNull(rangeEnd)) {
            return false;
        }

        return !date.isBefore(rangeStart) && !date.isAfter(rangeEnd);
    }

    public static boolean isWithinRange(LocalTime time,
                                        LocalTime rangeStart,
                                        LocalTime rangeEnd) {
        if (Validator.isNull(time) || Validator.isNull(rangeStart) ||
                Validator.isNull(rangeEnd)) {
            return false;
        }

        return !time.isBefore(rangeStart) && !time.isAfter(rangeEnd);
    }

    public static boolean isAlmostEqual(LocalDateTime dateTime1,
                                        LocalDateTime dateTime2,
                                        Duration tolerance) {
        if (Validator.isNull(dateTime1) || Validator.isNull(dateTime2) ||
                Validator.isNull(tolerance)) {
            return false;
        }

        Duration difference = Duration.between(dateTime1, dateTime2).abs();
        return difference.compareTo(tolerance) <= 0;
    }
}
