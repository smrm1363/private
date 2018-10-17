package com.mohammadreza.mirali.energyconsumption.domain.common;

import java.util.ArrayList;
import java.util.List;

public interface ValidatorInt {

    default <T> String doValidations(T t,List<ValidationRule> validationRuleList)
    {
        String message = null;
//        List<ValidationRule> validationRuleList = validationsFactory.getValidationRulesByPropertyName(validationsProperyKey);
//        validationRuleList.forEach(validationRule ->
//        {
//            try {
//                validationRule.validate(t);
//            } catch (ValidationException e) {
//                messages.add(e.getMessage());
//                e.printStackTrace();
//            }
//        });

        for(ValidationRule validationRule:validationRuleList)
        {
            try {
                validationRule.validate(t);
            } catch (ValidationException e) {

                e.printStackTrace();
                return e.getMessage();

            }
        }

        return null;

    }
}
