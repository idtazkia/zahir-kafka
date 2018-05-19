package id.ac.tazkia.zahir.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentRequest {
    private String transactionDate;
    private String customer;
    private String project;
    private String department;
    private List<PaymentRequestLineItem> lineItems = new ArrayList<>();
    private PaymentRequestCash cash;
    private String description;
}
