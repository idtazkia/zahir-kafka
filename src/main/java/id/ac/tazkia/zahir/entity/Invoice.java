package id.ac.tazkia.zahir.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data @Entity
public class Invoice {
    @Id
    private String id;
    @NotNull @NotEmpty
    private String salesInvoiceNumber;
    @NotNull @NotEmpty
    private String invoiceNumber;
    @NotNull @NotEmpty
    private String customer;
    @NotNull @Min(1)
    private BigDecimal amount;

    @NotNull @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus = InvoiceStatus.UNPAID;
}
