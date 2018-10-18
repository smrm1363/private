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

    public <T,B extends JpaRepository> void saveEntityListWithValidation(List<T> entityList, B repository,String validationsProperyKey) throws ValidationException {

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
        StringBuilder stringBuilder = new StringBuilder();
        allExceptionMessages.forEach(message -> {
            stringBuilder.append(message+" ; ");
        });
        if(allExceptionMessages.size()>0)
            throw new ValidationException(stringBuilder.toString());
    }
}
