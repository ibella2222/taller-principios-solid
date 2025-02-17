package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.valitator.ICompanyValidator;
import java.util.List;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class CompanyService implements ICompanyService {
    private final ICompanyRepository repository;
    private final ICompanyValidator validator;

    public CompanyService(ICompanyRepository repository, ICompanyValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public boolean createCompany(Company company) {
        if (validator.isValid(company)) {
            return repository.save(company);
        }
        return false; // Si no es válido, retorna false
    }
    @Override
    public List<Company> listCompanies() {
        return repository.listAll();
    }

    @Override
    public Company findCompanyByNit(String nit) {
        return repository.findByNit(nit);
    }
}



