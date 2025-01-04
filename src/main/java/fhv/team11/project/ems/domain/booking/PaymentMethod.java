package fhv.team11.project.ems.domain.booking;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PAYPAL("Bank Card"),
    CASH("Cash");

    private String name;

    PaymentMethod(String name) {
        this.name = name;
    }
}
