package com.mohammadreza.mirali.energyconsumption.domain.meter.validation;

import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingServiceTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 21/10/2018.
 */
public class ConsumptionToleranceValidationTest {
    @Test
    public void validate() throws Exception {
        MeterEntity meterEntity = MeterReadingServiceTest.getPreperedMeter();
    }

}