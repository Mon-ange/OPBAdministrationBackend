package ca.openbox.infrastructure.variables.service;

import ca.openbox.infrastructure.variables.entity.ApplicationVariable;
import ca.openbox.infrastructure.variables.repository.ApplicationVariablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationVariableService {
    @Autowired
    ApplicationVariablesRepository applicationVariablesRepository;
    public String getVariableValue(String key){
        try{
            return applicationVariablesRepository.getApplicationVariableByKey(key).getValue();
        }catch (Exception e){
            return null;
        }
    }
    public void setVariableValue(String key,String value){
        ApplicationVariable applicationVariable = new ApplicationVariable();
        applicationVariable.setKey(key);
        applicationVariable.setValue(value);
        applicationVariablesRepository.save(applicationVariable);
    }
}
