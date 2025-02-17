package co.edu.unicauca.mycompany.projects.domain.valitator;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import java.util.regex.Pattern;
/**
 *
 * @author ibella
 */
public class CompanyValidator implements ICompanyValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[@#$%^&+=!]).{6,}$";

    @Override
    public boolean isValid(Company company) {
        if (company == null) return false;

        return isValidNit(company.getNit()) &&
               isValidName(company.getName()) &&
               isValidSector(company.getSector()) &&
               isValidEmail(company.getEmail()) &&
               isValidPassword(company.getPassword());
    }

    private boolean isValidNit(String nit) {
        return nit != null && !nit.trim().isEmpty();
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidSector(Sector sector) {
        return sector != null; // Asegúrate de que el sector no sea nulo
    }

    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isValidPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }
}