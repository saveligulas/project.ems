package fhv.team11.project.ems.domain.commons;

public class CountryValidator {
    public static boolean isValid(String country){

        if(country == null || country.length() == 0){
            return false;
        }
        return true;
    }
}
