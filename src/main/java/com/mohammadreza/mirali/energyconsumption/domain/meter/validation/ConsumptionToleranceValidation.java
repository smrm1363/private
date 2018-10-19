package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;

import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;


public class ConsumptionToleranceValidation implements ValidationRule {
    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;
        Double tolerance = 0.25; //In real situation for more configurability, it could be loaded from a property file or database
        StringBuffer exceptionMessage = new StringBuffer("Consumption for a month should be consistent with " +
                "the fraction with a valid tolerance, Merer "+meterEntity.getId()+" has invalid values");
        final Boolean[] hasProblem = {false};
        meterEntity.getMeterReadingEntityList().forEach(meterReadingEntity -> {
            Double exactValidConsumption = meterEntity.getValue() * meterReadingEntity.getMatchedProfileFractionEntity().getFraction();
            Double maxValidConsumption = exactValidConsumption*(1+tolerance);
            Double minValidConsumption = exactValidConsumption*(1-tolerance);
            meterReadingEntity.setConsumtion(meterReadingEntity.calculateConsumption());
            if(meterReadingEntity.getConsumtion()<minValidConsumption || meterReadingEntity.getConsumtion()>maxValidConsumption)
            {
                hasProblem[0] = true;
                exceptionMessage.append(", "+meterReadingEntity.getMonth().toString());

            }
        });
        if(hasProblem[0])
        {
            exceptionMessage.append(", has/have problem");
            throw new ValidationException(exceptionMessage.toString());
        }

    }
}
