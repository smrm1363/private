package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by mmirali on 07/10/2018.
 */
@Entity
public class ProfileEntity {
    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<ProfileFractionEntity> profileFractionEntityList;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<MeterEntity> meterEntityList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<ProfileFractionEntity> getProfileFractionEntityList() {
        return profileFractionEntityList;
    }

    public void setProfileFractionEntityList(List<ProfileFractionEntity> profileFractionEntityList) {
        this.profileFractionEntityList = profileFractionEntityList;
    }

    public List<MeterEntity> getMeterEntityList() {
        return meterEntityList;
    }

    public void setMeterEntityList(List<MeterEntity> meterEntityList) {
        this.meterEntityList = meterEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileEntity profileEntity = (ProfileEntity) o;

        return id.equals(profileEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
