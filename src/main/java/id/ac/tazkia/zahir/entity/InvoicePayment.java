package id.ac.tazkia.zahir.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Entity
public class InvoicePayment {
    @Id
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @NotEmpty
    private String referenceNumber;

    @NotNull
    private LocalDateTime paymentTime = LocalDateTime.now();

    @NotNull @Min(1)
    private BigDecimal amount;
}
