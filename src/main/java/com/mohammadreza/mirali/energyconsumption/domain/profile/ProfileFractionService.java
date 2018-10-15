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
    private final RepositoryCompletion repositoryCompletion;

    @Autowired
    public ProfileFractionService(ProfileRepository profileRepository, ProfileFractionRepository profileFractionRepository, ValidationsFactory validationsFactory, RepositoryCompletion repositoryCompletion) {
        this.profileRepository = profileRepository;
        this.profileFractionRepository = profileFractionRepository;
        this.validationsFactory = validationsFactory;
        this.repositoryCompletion = repositoryCompletion;
    }

    public List<String> insertProfile(ProfileEntity profileEntity)
    {
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(profileEntity);
        return saveProfileList(profileEntityList);
    }

    @Override
    public List<String> convertToEntity(List dtoList) throws IOException {
        System.out.println("Here ...");
        List<ProfileFractionDto> profileFractionDtoList = dtoList;
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


            if(profileEntity.getProfileFractionEntityList()==null)
            {
                profileEntity.setProfileFractionEntityList(new ArrayList<ProfileFractionEntity>());
            }

            Boolean updated = false;
            for(ProfileFractionEntity profileFractionEntity1:profileEntity.getProfileFractionEntityList())
            {
                if(profileFractionEntity1.getProfileEntity().getId().equals(profileFractionDto.getProfile()))
                    if(profileFractionEntity1.getMonth() == MonthEnum.valueOf(profileFractionDto.getMonth()))
                    {
                        profileFractionEntity1.setFraction(profileFractionDto.getFraction());
                        updated = true;
                        break;
                    }
            }
            if(!updated)
            {
                ProfileFractionEntity profileFractionEntity = new ProfileFractionEntity();
                profileFractionEntity.setMonth(MonthEnum.valueOf(profileFractionDto.getMonth()));
                profileFractionEntity.setProfileEntity(profileEntity);
                profileFractionEntity.setFraction(profileFractionDto.getFraction());
                profileEntity.getProfileFractionEntityList().add(profileFractionEntity);
            }


            profileEntityMap.put(profileEntity.getId(),profileEntity);

        });
    return saveProfileList(new ArrayList<>(profileEntityMap.values()));
    }


    public List<String> saveProfileList(List<ProfileEntity> profileEntityList)
    {

       return repositoryCompletion.saveEntityListWithValidation(profileEntityList,profileRepository,validationsProperyKey);
//        List<ProfileEntity> selectedProfileEntityList = new ArrayList<>();
//        List<String> allExceptionMessages = new ArrayList<>();
//        profileEntityList.forEach(profileEntity -> {
//            List<String> exeptionMessages = doValidations(profileEntity,validationsFactory.getValidationRulesByPropertyName(validationsProperyKey));
//            if(exeptionMessages.size()>0)
//            {
//                allExceptionMessages.addAll(exeptionMessages);
//                return;
//            }
//            else
//            {
//                selectedProfileEntityList.add(profileEntity);
//            }
//
//        });
//
//            profileRepository.saveAll(selectedProfileEntityList);
//            return allExceptionMessages;


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
