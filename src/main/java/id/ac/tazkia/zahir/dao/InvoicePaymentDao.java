package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.InvoicePayment;
import org.springframework.data.repository.CrudRepository;

public interface InvoicePaymentDao extends CrudRepository<InvoicePayment, String> {
}
