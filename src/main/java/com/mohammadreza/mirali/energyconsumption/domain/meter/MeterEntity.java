package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class MeterEntity {
    @Id
    private String id;

    @NotNull
    @DecimalMin("0.0")
    Double value;

    @ManyToOne(fetch= FetchType.LAZY)
    ProfileEntity profileEntity;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<MeterReadingEntity> meterReadingEntityList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    public List<MeterReadingEntity> getMeterReadingEntityList() {
        return meterReadingEntityList;
    }

    public void setMeterReadingEntityList(List<MeterReadingEntity> meterReadingEntityList) {
        this.meterReadingEntityList = meterReadingEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterEntity that = (MeterEntity) o;

        if (!id.equals(that.id)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return profileEntity != null ? profileEntity.equals(that.profileEntity) : that.profileEntity == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}