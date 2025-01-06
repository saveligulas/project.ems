package fhv.team11.project.ems.domain.commons;

import java.time.LocalDate;
import java.time.LocalTime;

public class StartEndTimeValidator {

    public static boolean isValid(LocalTime start, LocalTime end){

        if(start.isAfter(end)){
            return false;
        }


        return true;
    }
}
