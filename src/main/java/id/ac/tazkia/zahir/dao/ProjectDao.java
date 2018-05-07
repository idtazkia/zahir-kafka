package id.ac.tazkia.zahir.dao;

import id.ac.tazkia.zahir.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectDao extends CrudRepository<Project, String> {
    Project findByCode(String code);
}
