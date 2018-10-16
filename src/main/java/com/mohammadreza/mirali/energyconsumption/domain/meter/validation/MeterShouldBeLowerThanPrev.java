package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;


import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationRule;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mmirali on 14/10/2018.
 */
public class MeterShouldBeLowerThanPrev implements ValidationRule {

    @Override
    public void validate(Object o) throws ValidationException {
        MeterEntity meterEntity = (MeterEntity) o;

        List<MeterReadingEntity> meterReadingEntityList = meterEntity.getMeterReadingEntityList().stream()
                .sorted((o1, o2) -> o1.getMonth().compareTo(o2.getMonth()) ).collect(Collectors.toList());
        for (int x=0;x<meterReadingEntityList.size();x++)
        {
            if(x>0)
            {
                if(meterReadingEntityList.get(x).getReadedMeter() < meterReadingEntityList.get(x-1).getReadedMeter())
                    throw new ValidationException("For a given meter ("+ meterEntity.getId() +") a reading for a month should not be lower than the previous one");

            }
        }

        //        Boolean result = false;
//        if(meterEntity.getMeterReadingEntityList()!= null) {
//            if (meterEntity.getMeterReadingEntityList().size() == 12)
//            {
//                result = true;
//            }
//        }
//        if(!result)
//            throw new ValidationException("Sum of fractions in profile "+meterEntity.getId()+"should be 1");

    }

//    @Override
//    public void validate(Object o) {
//
//    }
}
