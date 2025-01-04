package fhv.team11.project.ems.domain.commons;

public class OverbookPlacesValidator {
    public static boolean isValid(Integer places){

        if(places!=null){
            if(places<0){
                return false;
            }
        }

        return true;
    }
}
