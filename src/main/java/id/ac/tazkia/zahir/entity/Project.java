package id.ac.tazkia.zahir.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity @Data
public class Project {

    @Id
    private String id;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;
}
