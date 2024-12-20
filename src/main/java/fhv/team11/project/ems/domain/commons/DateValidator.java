package fhv.team11.project.ems.domain.commons;

import java.time.LocalDate;

public class DateValidator {
    public static boolean isValid(LocalDate date){

        if(date == null){
            return false;
        }

        if(date.isAfter(LocalDate.now())){
            return false;
        }

        return true;
    }
}
