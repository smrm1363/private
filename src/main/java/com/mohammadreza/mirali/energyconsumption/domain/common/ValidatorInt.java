package com.mohammadreza.mirali.energyconsumption.domain.common;


import java.util.List;

public interface ValidatorInt {

    default <T> String doValidations(T t,List<ValidationRule> validationRuleList)  {
        String message = null;


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
