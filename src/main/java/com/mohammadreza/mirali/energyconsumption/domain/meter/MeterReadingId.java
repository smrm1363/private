package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;

import java.io.Serializable;

/**
 * Created by mmirali on 15/10/2018.
 */

public class MeterReadingId implements Serializable {


    private MeterEntity meterEntity;
    private MonthEnum month;

    public MeterReadingId(MeterEntity meterEntity, MonthEnum month) {
        this.meterEntity = meterEntity;
        this.month = month;
    }

    public MeterReadingId() {

    }
}
