package com.mohammadreza.mirali.energyconsumption.domain.profile;

public class ProfileFractionDto {
    String month;
    String profile;
    Double fraction;

    public ProfileFractionDto(String month, String profile, Double fraction) {
        this.month = month;
        this.profile = profile;
        this.fraction = fraction;
    }

    public ProfileFractionDto() {
        
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Double getFraction() {
        return fraction;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }
}
