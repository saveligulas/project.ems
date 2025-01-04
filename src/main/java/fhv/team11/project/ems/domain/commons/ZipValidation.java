package fhv.team11.project.ems.domain.commons;

public class ZipValidation {
    public static boolean isValid(Integer zip){

        if (zip == null) {
            return false;
        }

        if(String.valueOf(zip).length()<4 || String.valueOf(zip).length()>5){
            return false;
        }

        return true;
    }
}
