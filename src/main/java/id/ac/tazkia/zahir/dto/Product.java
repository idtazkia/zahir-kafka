package id.ac.tazkia.zahir.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Product {
    private String id;
    private String code;
    private String name;
}
