package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReadingEntity,String> {
    List<MeterReadingEntity> findByMeterEntityIdAndMonth(String meterEntity_id, MonthEnum month);
}

