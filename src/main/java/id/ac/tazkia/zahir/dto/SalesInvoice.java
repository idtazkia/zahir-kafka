package id.ac.tazkia.zahir.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
public class SalesInvoice {
    private String id;
    private String invoiceDate;
    private String invoiceNumber;
    private Customer customer;
    private Department department;
    private Project project;
    private List<Product> lineItems;
}
