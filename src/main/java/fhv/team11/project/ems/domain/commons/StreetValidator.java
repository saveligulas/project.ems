package fhv.team11.project.ems.domain.commons;

public class StreetValidator {
    public static boolean isValid(String street){

        if(street == null || street.length() == 0){
            return false;
        }
        return true;
    }
}
