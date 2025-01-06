package fhv.team11.project.ems.domain.commons;

public class ParticipantsValidation {
    public static boolean isValid(Integer participants){

        if (participants == null) {
            return false;
        }

        if(participants <= 0 || participants > 1000){
            return false;
        }

        return true;
    }
}
