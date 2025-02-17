package co.edu.unicauca.mycompany.projects.access;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
/**
 *
 * @author ibella
 */
public interface ICompanyWriter {
    boolean save(Company newCompany);
    boolean update(Company company);
}
