package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.IncomingInvoice;
import org.springframework.data.repository.CrudRepository;

public interface IncomingInvoiceDao extends CrudRepository<IncomingInvoice, String> {
}
