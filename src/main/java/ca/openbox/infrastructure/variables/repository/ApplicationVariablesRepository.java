package ca.openbox.infrastructure.variables.repository;

import ca.openbox.infrastructure.variables.entity.ApplicationVariable;
import org.springframework.data.repository.Repository;


public interface ApplicationVariablesRepository extends Repository<ApplicationVariable,String> {
    public void save(ApplicationVariable applicationVariable);
    public ApplicationVariable getApplicationVariableByKey(String key);
}
