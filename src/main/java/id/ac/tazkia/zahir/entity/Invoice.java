package id.ac.tazkia.zahir.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data @Entity
public class Invoice {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty
    private String salesInvoice;
    @NotNull @NotEmpty
    private String salesInvoiceNumber;
    @NotNull @NotEmpty
    private String invoiceNumber;
    @NotNull @NotEmpty
    private String customer;
    @NotNull @Min(1)
    private BigDecimal amount;

}
