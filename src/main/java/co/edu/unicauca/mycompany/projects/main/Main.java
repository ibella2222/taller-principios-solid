package co.edu.unicauca.mycompany.projects.main;

import co.edu.unicauca.mycompany.projects.access.CompanyArraysRepository;
import co.edu.unicauca.mycompany.projects.access.CompanySqliteRepository;
import co.edu.unicauca.mycompany.projects.access.Factory;
import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyService;
import co.edu.unicauca.mycompany.projects.domain.services.ICompanyService;
import co.edu.unicauca.mycompany.projects.domain.valitator.CompanyValidator;
import co.edu.unicauca.mycompany.projects.domain.valitator.ICompanyValidator;
import co.edu.unicauca.mycompany.projects.presentation.GUIMenu;
import javax.swing.JFrame;

/**
 * 
 * @author Libardo Pantoja
 * 
 */
 public class Main {
    public static void main(String[] args) {
        // Obtener la instancia de la fábrica y registrar repositorios
        Factory factory = Factory.getInstance();
        factory.registerRepository("ARRAYS", new CompanyArraysRepository());
        factory.registerRepository("SQLITE", new CompanySqliteRepository());

        // Crear el servicio con la implementación seleccionada
        ICompanyRepository repository = factory.getRepository("ARRAYS");
        ICompanyValidator validator = new CompanyValidator();
        ICompanyService service = new CompanyService(repository, validator);

        // Crear y mostrar la interfaz gráfica
        GUIMenu instance = new GUIMenu((CompanyService) service);
        instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
        instance.setVisible(true);
    }
}

