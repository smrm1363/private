package com.mohammadreza.mirali.energyconsumption.domain.meter;

public class MeterReadingDto {
    String meterID;
    String profile;
    String month;
    Double meterReading;

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(Double meterReading) {
        this.meterReading = meterReading;
    }
}
