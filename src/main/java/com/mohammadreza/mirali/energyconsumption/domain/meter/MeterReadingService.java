package com.mohammadreza.mirali.energyconsumption.domain.meter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MeterReadingService")
public class MeterReadingService {

    private final MeterRepository meterRepository;
    private final MeterReadingRepository meterReadingRepository;

    @Autowired
    public MeterReadingService(MeterRepository meterRepository, MeterReadingRepository meterReadingRepository) {
        this.meterRepository = meterRepository;
        this.meterReadingRepository = meterReadingRepository;
    }



}
