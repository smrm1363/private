package com.mohammadreza.mirali.energyconsumption.domain.meter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReadingEntity,String> {
}
