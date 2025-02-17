package co.edu.unicauca.mycompany.projects.access;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Libardo, Julio
 */

public class Factory implements IRepositoryFactory {
    private static Factory  instance;
    private final Map<String, ICompanyRepository> repositories;

    private Factory () {
        repositories = new HashMap<>();
    }

    public static Factory  getInstance() {
        if (instance == null) {
            instance = new Factory ();
        }
        return instance;
    }

    @Override
    public void registerRepository(String key, ICompanyRepository repository) {
        repositories.put(key, repository);
    }

    @Override
    public ICompanyRepository getRepository(String key) {
        return repositories.getOrDefault(key, null);
    }
}