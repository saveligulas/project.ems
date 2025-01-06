package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.domain.booking.InvoiceDelivery;
import fhv.team11.project.ems.domain.booking.PaymentMethod;
import fhv.team11.project.ems.domain.booking.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class InvoiceEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceDelivery delivery;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    private BigDecimal amount;

    @Column(unique = true)
    private UUID identifier;
    private LocalDate dueDate;
    private LocalDateTime paymentDate;
}
