package fhv.team11.project.ems.domain.commons;

import java.time.LocalTime;

public class TimeValidator {

    public static boolean isValid(LocalTime date){

        if(date == null){
            return false;
        }


        return true;
    }
}
