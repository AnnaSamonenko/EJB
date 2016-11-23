package dao.interfaces;

import entities.Company;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CompanyDAO {
    void create(Company company);

    List<Company> findAllCompanies();

    void update(int id, Company company);

    void delete(int id);

    Company read(int id);
}
