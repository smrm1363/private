package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service("ProfileFractionService")
public class ProfileFractionService implements ConvertFileToEntityInt, ValidatorInt {
    private final String validationsProperyKey = "profile.fraction.validation";
    private final ProfileRepository profileRepository;
    private final ProfileFractionRepository profileFractionRepository;
    private final ValidationsFactory validationsFactory;

    @Autowired
    public ProfileFractionService(ProfileRepository profileRepository, ProfileFractionRepository profileFractionRepository, ValidationsFactory validationsFactory) {
        this.profileRepository = profileRepository;
        this.profileFractionRepository = profileFractionRepository;
        this.validationsFactory = validationsFactory;
    }

    public void insertProfile(ProfileEntity profileEntity)
    {
        profileRepository.save(profileEntity);
    }

    @Override
    public List<String> convertToEntity(List dtoList) throws IOException {
        System.out.println("Here ...");
        List<ProfileFractionDto> profileFractionDtoList = dtoList;
        List<String> allExceptionMessages = new ArrayList<>();
        Map<String,ProfileEntity> profileEntityMap = new HashMap<>();
        profileFractionDtoList.forEach(profileFractionDto ->
        {
            ProfileEntity profileEntity = profileEntityMap.get(profileFractionDto.getProfile());

            if(profileEntity == null) {
                Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileFractionDto.getProfile());
                if (profileEntityOptional.isPresent()) {
                    profileEntity = profileEntityOptional.get();
                } else {
                    profileEntity = new ProfileEntity();
                    profileEntity.setId(profileFractionDto.getProfile());
                }
            }
            ProfileFractionEntity profileFractionEntity = new ProfileFractionEntity();
            profileFractionEntity.setFraction(profileFractionDto.getFraction());
            profileFractionEntity.setMonth(MonthEnum.valueOf(profileFractionDto.getMonth()));
            profileFractionEntity.setProfileEntity(profileEntity);
            if(profileEntity.getProfileFractionEntityList()==null)
            {
                profileEntity.setProfileFractionEntityList(new ArrayList<ProfileFractionEntity>());
            }

            profileEntity.getProfileFractionEntityList().add(profileFractionEntity);
            profileEntityMap.put(profileEntity.getId(),profileEntity);
            List<String> exeptionMessages = doValidations(profileEntity,validationsFactory.getValidationRulesByPropertyName(validationsProperyKey));
            if(exeptionMessages.size()>0)
            {
                allExceptionMessages.addAll(exeptionMessages);
                return;
            }
            profileRepository.save(profileEntity);
        });
    return allExceptionMessages;
    }

    @Override
    public Map<String, String> getColumnMapping() {
        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("Month", "month");
        columnMapping.put("Profile", "profile");
        columnMapping.put("Fraction", "fraction");

        return columnMapping;
    }



}
