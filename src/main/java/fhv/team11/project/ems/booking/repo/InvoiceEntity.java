package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.domain.booking.InvoiceDelivery;
import fhv.team11.project.ems.domain.booking.PaymentMethod;
import fhv.team11.project.ems.domain.booking.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financer_id",nullable = true)
    private CustomerProfileEntity customerProfile;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private RealWorldEntity seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private RealWorldEntity buyer;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceDelivery invoiceDelivery;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<LineItemEntity> lineItems;

    @Column(unique = true)
    private UUID identifier;

    private LocalDate dueDate;
    private LocalDate createdDate;
    private LocalDate supplyDate;
    private LocalDateTime paymentDate;
}
