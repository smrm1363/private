package com.mohammadreza.mirali.energyconsumption.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("ValidationsFactory")
public class ValidationsFactory {

    private final Environment env;

    @Autowired
    public ValidationsFactory(Environment env) {
        this.env = env;
    }

    public List<ValidationRule> getValidationRulesByPropertyName(String propertyKey)
    {
        String[] validationArray = env.getProperty(propertyKey).split(",");
        List<ValidationRule> validationRuleList = new ArrayList<>();
        Arrays.stream(validationArray).forEach(s -> {


            try {
                validationRuleList.add((ValidationRule) Class.forName(s).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });
        return validationRuleList;
    }
}
