package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by mmirali on 15/10/2018.
 */

public class ProfileFractionId implements Serializable {


    private ProfileEntity profileEntity;
    private MonthEnum month;

    public ProfileFractionId(ProfileEntity profileEntity, MonthEnum month) {
        this.profileEntity = profileEntity;
        this.month = month;
    }

    public ProfileFractionId() {

    }
}
