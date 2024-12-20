package fhv.team11.project.ems.domain.commons;

public class MaxParticipantsValidation {
    public static boolean isValid(Integer maxParticipants){

        if (maxParticipants == null) {
            return false;
        }

        if(maxParticipants <= 0 || maxParticipants > 1000){
            return false;
        }


        return true;
    }
}
