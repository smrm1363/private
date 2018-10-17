package com.mohammadreza.mirali.energyconsumption.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RepositoryCompletion implements ValidatorInt{

    private final ValidationsFactory validationsFactory;
    @Autowired
    public RepositoryCompletion(ValidationsFactory validationsFactory) {
        this.validationsFactory = validationsFactory;
    }

    public <T,B extends JpaRepository> List<String> saveEntityListWithValidation(List<T> entityList, B repository,String validationsProperyKey)
    {

        List<T> selectedEntityList = new ArrayList<>();
        List<String> allExceptionMessages = new ArrayList<>();
        entityList.forEach(entity -> {
            String exeptionMessage = doValidations(entity,validationsFactory.getValidationRulesByPropertyName(validationsProperyKey));
            if(exeptionMessage != null)
            {
                allExceptionMessages.add(exeptionMessage);
                return;
            }
            else
            {
                selectedEntityList.add(entity);
            }

        });

        repository.saveAll(selectedEntityList);
        return allExceptionMessages;
    }
}
