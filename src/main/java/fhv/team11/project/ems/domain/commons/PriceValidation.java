package fhv.team11.project.ems.domain.commons;

public class PriceValidation {
    public static boolean isValid(Integer price){

        if (price == null) {
            return false;
        }

        if(price <= 0){
            return false;
        }

        return true;
    }
}
