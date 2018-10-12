package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.MonthEnum;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class MeterReadingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    MeterEntity meterEntity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MonthEnum month;

    @NotNull
    @DecimalMin("0.0")
    private Double readedMeter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeterEntity getMeterEntity() {
        return meterEntity;
    }

    public void setMeterEntity(MeterEntity meterEntity) {
        this.meterEntity = meterEntity;
    }

    public MonthEnum getMonth() {
        return month;
    }

    public void setMonth(MonthEnum month) {
        this.month = month;
    }

    public Double getReadedMeter() {
        return readedMeter;
    }

    public void setReadedMeter(Double readedMeter) {
        this.readedMeter = readedMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterReadingEntity that = (MeterReadingEntity) o;

        if (!id.equals(that.id)) return false;
        if (!meterEntity.equals(that.meterEntity)) return false;
        if (month != that.month) return false;
        return readedMeter.equals(that.readedMeter);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}