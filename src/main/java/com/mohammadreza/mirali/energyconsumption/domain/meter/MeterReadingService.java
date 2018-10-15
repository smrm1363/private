package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.*;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("MeterReadingService")
public class MeterReadingService implements ConvertFileToEntityInt, ValidatorInt {

    private final String validationsProperyKey = "meter.reading.validations";
    private final MeterRepository meterRepository;
    private final MeterReadingRepository meterReadingRepository;
    private final ProfileRepository profileRepository;
    private final ValidationsFactory validationsFactory;
    private final RepositoryCompletion repositoryCompletion;


    @Autowired
    public MeterReadingService(MeterRepository meterRepository, MeterReadingRepository meterReadingRepository, ProfileRepository profileRepository, ValidationsFactory validationsFactory, RepositoryCompletion repositoryCompletion) {
        this.meterRepository = meterRepository;
        this.meterReadingRepository = meterReadingRepository;
        this.profileRepository = profileRepository;
        this.validationsFactory = validationsFactory;
        this.repositoryCompletion = repositoryCompletion;
    }

    public List<String> insertMeter(MeterEntity meterEntity)
    {
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(meterEntity);
        return saveMeterList(meterEntityList);
    }

    @Override
    public List<String> convertToEntity(List dtoList) throws IOException {

        List<MeterReadingDto> meterReadingDtoList = dtoList;
        List<String> allExceptionMessages = new ArrayList<>();
        Map<String,MeterEntity> meterEntityMap = new HashMap<>();
        meterReadingDtoList.stream().sorted((o1, o2) -> MonthEnum.valueOf(o1.month).compareTo(MonthEnum.valueOf(o2.month)))
                .forEach(meterReadingDto ->
        {

            MeterEntity meterEntity = meterEntityMap.get(meterReadingDto.getMeterID());

            if(meterEntity == null) {
                Optional<MeterEntity> meterEntityOptional = meterRepository.findById(meterReadingDto.getMeterID());
                if (meterEntityOptional.isPresent()) {
                    meterEntity = meterEntityOptional.get();
                } else {
                    meterEntity = new MeterEntity();
                    meterEntity.setId(meterReadingDto.getMeterID());
                }
            }
            meterEntity.setValue(meterReadingDto.getMeterReading());
            ProfileEntity profileEntity = null;
            Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(meterReadingDto.getProfile());
            if(profileEntityOptional.isPresent())
                profileEntity = profileEntityOptional.get();
//            else
//            {
//                // TODO: 10/12/2018
//                System.out.println("Profile is not present");
//                return;
//            }
            meterEntity.setProfileEntity(profileEntity);

            if(meterEntity.getMeterReadingEntityList()==null)
            {
                meterEntity.setMeterReadingEntityList(new ArrayList<MeterReadingEntity>());
            }
            Boolean updated = false;
            for(MeterReadingEntity meterReadingEntity:meterEntity.getMeterReadingEntityList())
            {
                if(meterReadingEntity.getMeterEntity().getId().equals(meterReadingDto.getMeterID()))
                    if(meterReadingEntity.getMonth() == MonthEnum.valueOf(meterReadingDto.getMonth()))
                    {
                        meterReadingEntity.setReadedMeter(meterReadingDto.getMeterReading());
                        updated = true;
                        break;
                    }
            }

            if(!updated) {
                MeterReadingEntity meterReadingEntity = new MeterReadingEntity();
                meterReadingEntity.setReadedMeter(meterReadingDto.getMeterReading());
                meterReadingEntity.setMonth(MonthEnum.valueOf(meterReadingDto.getMonth()));
                meterReadingEntity.setMeterEntity(meterEntity);
                meterEntity.getMeterReadingEntityList().add(meterReadingEntity);
            }


            meterEntityMap.put(meterEntity.getId(),meterEntity);


        });
        return saveMeterList(new ArrayList<>(meterEntityMap.values()));
    }



    public List<String> saveMeterList(List<MeterEntity> meterEntityList) {

        return repositoryCompletion.saveEntityListWithValidation(meterEntityList, profileRepository, validationsProperyKey);
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
//
//    private List<String> doValidations(MeterEntity meterEntity)
//    {
//        List<String> messages = new ArrayList<>();
//        List<ValidationRule> validationRuleList = validationsFactory.getValidationRulesByPropertyName(validationsProperyKey);
//        validationRuleList.forEach(validationRule ->
//        {
//            try {
//                validationRule.validate(meterEntity);
//            } catch (MeterException e) {
//                e.printStackTrace();
//                messages.add(e.getMessage());
//            } catch (ValidationException e) {
//                e.printStackTrace();
//            }
//        });
//        return messages;
//
//    }
}
