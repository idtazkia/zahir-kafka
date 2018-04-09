package id.ac.tazkia.zahir.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

@Data @ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Payment {
    private String id;
    private String referenceNumber;
    private String transactionDate;
    private String description;
    private Customer customer;

}
