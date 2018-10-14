package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;


import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;

/**
 * Created by mmirali on 14/10/2018.
 */
public class MeterShouldBeLowerThanPrev implements ValidationRule {

    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;
        Boolean result = false;
        if(meterEntity.getMeterReadingEntityList()!= null) {
            if (meterEntity.getMeterReadingEntityList().size() == 12)
            {
                result = true;
            }
        }
        if(!result)
            throw new ValidationException("Sum of fractions in profile "+meterEntity.getId()+"should be 1");

    }

//    @Override
//    public void validate(Object o) {
//
//    }
}
