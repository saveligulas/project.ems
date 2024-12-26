package fhv.team11.project.ems.domain.commons;

public class IndexValidator {
    public static boolean isValid(Long id){

        if(id != null){
            if(id <= 0){
                return false;
            }
        }

        return true;
    }
}
