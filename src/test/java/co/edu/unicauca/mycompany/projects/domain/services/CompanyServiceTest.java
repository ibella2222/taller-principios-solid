package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.domain.valitator.ICompanyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

public class CompanyServiceTest {
    
    @Mock
    private ICompanyRepository repositoryMock;
    
    @Mock
    private ICompanyValidator validatorMock;
    
    private ICompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyService(repositoryMock, validatorMock);
    }

    @Test
    void testListCompanies() {
        List<Company> companies = Arrays.asList(
            new Company("123456", "Empresa A", "3434343", "www.mipagina1.com", Sector.SERVICES, "gerente1@gmail.com", "123"),
            new Company("123457", "Empresa B", "3434344", "www.mipagina2.com", Sector.SERVICES, "gerente2@gmail.com", "123"),
            new Company("123458", "Empresa C", "3434345", "www.mipagina3.com", Sector.SERVICES, "gerente3@gmail.com", "123")
        );
        
        when(repositoryMock.listAll()).thenReturn(companies);

        List<Company> result = companyService.listCompanies();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Empresa A", result.get(0).getName());
        assertEquals("Empresa B", result.get(1).getName());

        verify(repositoryMock, times(1)).listAll();
    }

    @Test
    void testCreateCompany_Success() {
        Company newCompany = new Company("123459", "Empresa D", "3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "Password1@");
        
        when(validatorMock.isValid(newCompany)).thenReturn(true);
        when(repositoryMock.save(newCompany)).thenReturn(true);

        boolean result = companyService.createCompany(newCompany);

        assertTrue(result);
        verify(validatorMock, times(1)).isValid(newCompany);
        verify(repositoryMock, times(1)).save(newCompany);
    }

    @Test
    void testCreateCompany_Failure_ValidationFails() {
        Company newCompany = new Company("123459", "Empresa D", "3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "Password1@");
        
        when(validatorMock.isValid(newCompany)).thenReturn(false);
        
        boolean result = companyService.createCompany(newCompany);

        assertFalse(result);
        verify(validatorMock, times(1)).isValid(newCompany);
        verify(repositoryMock, never()).save(newCompany);
    }

    @Test
    void testCreateCompany_Failure_SaveFails() {
        Company newCompany = new Company("123459", "Empresa D", "3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "Password1@");
        
        when(validatorMock.isValid(newCompany)).thenReturn(true);
        when(repositoryMock.save(newCompany)).thenReturn(false);
        
        boolean result = companyService.createCompany(newCompany);

        assertFalse(result);
        verify(validatorMock, times(1)).isValid(newCompany);
        verify(repositoryMock, times(1)).save(newCompany);
    }

    @Test
    void testCreateCompany_Failure_InvalidEmail() {
        Company newCompany = new Company("123459", "Empresa D", "3434345", "invalid-email", Sector.SERVICES, "Password1@", "123");

        when(validatorMock.isValid(newCompany)).thenReturn(false);

        boolean result = companyService.createCompany(newCompany);

        assertFalse(result);
        verify(validatorMock, times(1)).isValid(newCompany);
        verify(repositoryMock, never()).save(newCompany);
    }

    @Test
    void testCreateCompany_Failure_InvalidPassword() {
        Company newCompany = new Company("123459", "Empresa D", "3434345", "gerente4@gmail.com", Sector.SERVICES, "pass", "123");

        when(validatorMock.isValid(newCompany)).thenReturn(false);

        boolean result = companyService.createCompany(newCompany);

        assertFalse(result);
        verify(validatorMock, times(1)).isValid(newCompany);
        verify(repositoryMock, never()).save(newCompany);
    }

    @Test
    void testFindCompanyByNit_Success() {
        String nit = "123459";
        Company expectedCompany = new Company(nit, "Empresa D", "3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "Password1@");

        when(repositoryMock.findByNit(nit)).thenReturn(expectedCompany);

        Company result = companyService.findCompanyByNit(nit);

        assertNotNull(result);
        assertEquals(expectedCompany.getNit(), result.getNit());
        assertEquals(expectedCompany.getName(), result.getName());
        verify(repositoryMock, times(1)).findByNit(nit);
    }

    @Test
    void testFindCompanyByNit_NotFound() {
        String nit = "123460";

        when(repositoryMock.findByNit(nit)).thenReturn(null);

        Company result = companyService.findCompanyByNit(nit);

        assertNull(result);
        verify(repositoryMock, times(1)).findByNit(nit);
    }
}


