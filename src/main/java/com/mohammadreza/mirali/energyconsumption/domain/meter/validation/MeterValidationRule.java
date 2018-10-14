package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;

import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;

/**
 * Created by mmirali on 14/10/2018.
 */
@FunctionalInterface
public interface  MeterValidationRule {
    void validate(ProfileEntity profileEntity);

}
