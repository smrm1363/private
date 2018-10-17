package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@IdClass(MeterReadingId.class)
public class MeterReadingEntity {
//    @Id
//    @GeneratedValue
//    private Long id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    MeterEntity meterEntity;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    private MonthEnum month;

    @NotNull
    @DecimalMin("0.0")
    private Double readedMeter;



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

    public Double getConsumption()
    {
        if(month.equals(MonthEnum.JAN))
            return readedMeter;
        else
        {
             Optional<MeterReadingEntity> meterReadingEntity = meterEntity.getMeterReadingEntityList().stream().filter(meterReadingEntity1 ->
             meterReadingEntity1.getMonth().equals(month.prev())).findFirst();
             return readedMeter - meterReadingEntity.get().getReadedMeter();

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterReadingEntity that = (MeterReadingEntity) o;

        if (!meterEntity.equals(that.meterEntity)) return false;
        if (month != that.month) return false;
        return readedMeter.equals(that.readedMeter);
    }

    @Override
    public int hashCode() {
        int result = meterEntity.hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }
}
