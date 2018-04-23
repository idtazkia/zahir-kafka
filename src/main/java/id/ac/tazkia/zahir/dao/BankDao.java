package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankDao extends CrudRepository<Bank, String> {
}
