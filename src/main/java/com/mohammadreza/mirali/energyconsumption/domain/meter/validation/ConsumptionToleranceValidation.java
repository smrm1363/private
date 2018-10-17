package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;

import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;

/**
 * Created by mmirali on 17/10/2018.
 */
public class ConsumptionToleranceValidation implements ValidationRule {
    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;
        meterEntity.getValue();
        return;
    }
}
