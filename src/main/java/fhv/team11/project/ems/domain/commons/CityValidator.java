package fhv.team11.project.ems.domain.commons;

public class CityValidator {

    public static boolean isValid(String city){

        if(city == null || city.length() == 0){
            return false;
        }
        return true;
    }
}
