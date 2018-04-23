package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDao extends CrudRepository<Invoice, String> {
    Invoice findByInvoiceNumber(String nomorTagihan);
}
