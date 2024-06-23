package ca.openbox.infrastructure.varaibles.repository;

import ca.openbox.infrastructure.varaibles.entity.ApplicationVariable;
import org.springframework.data.repository.Repository;


public interface ApplicationVariablesRepository extends Repository<ApplicationVariable,String> {
    public void save(ApplicationVariable applicationVariable);
    public ApplicationVariable getApplicationVariableByKey(String key);
}
