package fhv.team11.project.ems.domain.commons;

public class RegionValidator {
    public static boolean isValid(String region){

        if(region == null || region.length() == 0){
            return false;
        }
        return true;
    }
}
