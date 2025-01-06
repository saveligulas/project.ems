package fhv.team11.project.ems.domain.booking;

import lombok.Getter;

@Getter
public enum InvoiceDelivery {
    EMAIL("Email"),
    MOBILE_PHONE("Mobile Phone"),
    COURIER_DELIVERY("Courier Delivery");

    private final String name;

    InvoiceDelivery(String name) {
        this.name = name;
    }
}

