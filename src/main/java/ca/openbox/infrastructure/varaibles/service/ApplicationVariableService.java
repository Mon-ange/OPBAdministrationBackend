package ca.openbox.infrastructure.varaibles.service;

import ca.openbox.infrastructure.varaibles.entity.ApplicationVariable;
import ca.openbox.infrastructure.varaibles.repository.ApplicationVariablesRepository;
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
