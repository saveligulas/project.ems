package fhv.team11.project.ems.booking.service.validators;

import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(BookingDTO.class)
public class BookingDTOValidator implements IDomainValidator<BookingDTO> {

    private final Validator validator;

    @Autowired
    public BookingDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    private static class BookingtValidation{

    }
    @Override
    public BindingResult validate(BookingDTO bookingDTO) {
        return null;
    }
}
