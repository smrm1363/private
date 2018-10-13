package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.ConvertFileToEntityInt;
import com.mohammadreza.mirali.energyconsumption.domain.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("MeterReadingService")
public class MeterReadingService implements ConvertFileToEntityInt {

    private final MeterRepository meterRepository;
    private final MeterReadingRepository meterReadingRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public MeterReadingService(MeterRepository meterRepository, MeterReadingRepository meterReadingRepository, ProfileRepository profileRepository) {
        this.meterRepository = meterRepository;
        this.meterReadingRepository = meterReadingRepository;
        this.profileRepository = profileRepository;
    }


    @Override
    public void convertToEntity(List dtoList) throws IOException {

        List<MeterReadingDto> meterReadingDtoList = dtoList;
        meterReadingDtoList.stream().sorted((o1, o2) -> MonthEnum.valueOf(o1.month).compareTo(MonthEnum.valueOf(o2.month)))
                .forEach(meterReadingDto ->
        {
            MeterEntity meterEntity;
            Optional<MeterEntity> meterEntityOptional = meterRepository.findById(meterReadingDto.getMeterID());
            if(meterEntityOptional.isPresent())
            {
                meterEntity = meterEntityOptional.get();
            }
            else
            {
                meterEntity = new MeterEntity();
                meterEntity.setId(meterReadingDto.getMeterID());


            }
            meterEntity.setValue(meterReadingDto.getMeterReading());
            ProfileEntity profileEntity;
            Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(meterReadingDto.getProfile());
            if(profileEntityOptional.isPresent())
                profileEntity = profileEntityOptional.get();
            else
            {
                // TODO: 10/12/2018
                System.out.println("Profile is not present");
                return;
            }
            meterEntity.setProfileEntity(profileEntity);


            MeterReadingEntity meterReadingEntity = new MeterReadingEntity();
            meterReadingEntity.setReadedMeter(meterReadingDto.getMeterReading());
            meterReadingEntity.setMonth(MonthEnum.valueOf(meterReadingDto.getMonth()));
            meterReadingEntity.setMeterEntity(meterEntity);
            if(meterEntity.getMeterReadingEntityList()==null)
            {
                meterEntity.setMeterReadingEntityList(new ArrayList<MeterReadingEntity>());
            }

            meterEntity.getMeterReadingEntityList().add(meterReadingEntity);
            meterRepository.save(meterEntity);
        });
    }

    @Override
    public Map<String, String> getColumnMapping() {
        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("MeterID", "meterID");
        columnMapping.put("Profile", "profile");
        columnMapping.put("Month", "month");
        columnMapping.put("Meter reading", "meterReading");
        return columnMapping;
    }
}
