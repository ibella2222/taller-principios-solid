
package co.edu.unicauca.mycompany.projects.access;


import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public interface ICompanyRepository extends ICompanyReader, ICompanyWriter {
    @Override
    boolean save(Company newCompany);

    @Override
    List<Company> listAll();
}
