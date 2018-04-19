package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.InvoiceConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceConfigurationDao extends CrudRepository<InvoiceConfiguration, String> {
    InvoiceConfiguration findByInvoiceType(String invoiceType);
}
