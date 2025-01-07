package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookingDTO implements IModelAttribute {
    @NotNull
    private Long financer;
    @NotNull
    private Integer bookedPlaces;
    private PaymentOptionDTO paymentOption = new PaymentOptionDTO();
}
