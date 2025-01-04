package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.Data;

@Data
public class CreateBookingDTO implements IModelAttribute {
    private Long financer;
    private Integer bookedPlaces;
    private PaymentOptionDTO paymentOption = new PaymentOptionDTO();
}
