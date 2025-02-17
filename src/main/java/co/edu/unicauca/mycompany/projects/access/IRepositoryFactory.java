/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.access;

/**
 *
 * @author ibell
 */
public interface IRepositoryFactory {
    void registerRepository(String key, ICompanyRepository repository);
    ICompanyRepository getRepository(String key);
}
