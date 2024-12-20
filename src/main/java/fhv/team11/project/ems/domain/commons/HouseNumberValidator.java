package fhv.team11.project.ems.domain.commons;

public class HouseNumberValidator {

    public static boolean isValid(String houseNumber){

        if(houseNumber == null || houseNumber.length() == 0){
            return false;
        }
        return true;
    }

}
