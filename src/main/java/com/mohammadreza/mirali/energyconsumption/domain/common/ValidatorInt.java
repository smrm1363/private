package com.mohammadreza.mirali.energyconsumption.domain.common;

import java.util.ArrayList;
import java.util.List;

public interface ValidatorInt {

    default <T> List<String> doValidations(T t,List<ValidationRule> validationRuleList)
    {
        List<String> messages = new ArrayList<>();
//        List<ValidationRule> validationRuleList = validationsFactory.getValidationRulesByPropertyName(validationsProperyKey);
        validationRuleList.forEach(validationRule ->
        {
            try {
                validationRule.validate(t);
            } catch (ValidationException e) {
                messages.add(e.getMessage());
                e.printStackTrace();
            }
        });
        return messages;

    }
}
