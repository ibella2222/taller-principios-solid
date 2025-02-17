package co.edu.unicauca.mycompany.projects.access;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;
/**
 *
 * @author ibella
 */
public interface ICompanyReader {
    List<Company> listAll();
    Company findByNit(String nit);
}