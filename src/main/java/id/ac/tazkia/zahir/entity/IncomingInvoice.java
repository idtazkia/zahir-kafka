package id.ac.tazkia.zahir.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Data
public class IncomingInvoice {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String debitur;
    private String jenisTagihan;
    private String kodeBiaya;
    private BigDecimal nilaiTagihan;
    private LocalDate tanggalTagihan;
    private LocalDate tanggalJatuhTempo;
    private String nomorTagihan;
    private String keterangan;

    @Enumerated(EnumType.STRING)
    private IncomingInvoiceStatus status = IncomingInvoiceStatus.NEW;
}
