package id.ac.tazkia.zahir.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SalesInvoiceRequest {
    private String invoiceDate;
    private String invoiceNumber;
    private String customer;
    private String department;
    private String project;
    private Boolean isPosted;
    private List<SalesInvoiceRequestLineItem> lineItems;
    private TermOfPayment termOfPayment;
}
