package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;


import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;

/**
 * Created by mmirali on 14/10/2018.
 */
public class SumFractionValidation implements MeterValidationRule {

    @Override
    public void validate(ProfileEntity profileEntity) throws MeterException {
        Boolean result = false;
        if(profileEntity.getProfileFractionEntityList()!= null) {
            if (profileEntity.getProfileFractionEntityList().size() == 12)
                result = true;
        }
        if(!result)
            throw new MeterException();

    }
}
