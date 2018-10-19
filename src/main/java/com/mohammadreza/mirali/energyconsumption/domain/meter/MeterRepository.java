package com.mohammadreza.mirali.energyconsumption.domain.meter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepository extends JpaRepository<MeterEntity,String> {
    List<MeterEntity> findByProfileEntityId(String profileEntity_id);
}
