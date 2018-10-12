package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.ConvertFileToEntityInt;
import com.mohammadreza.mirali.energyconsumption.domain.MonthEnum;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("ProfileFractionService")
public class ProfileFractionService implements ConvertFileToEntityInt {
    private final ProfileRepository profileRepository;
    private final ProfileFractionRepository profileFractionRepository;

    @Autowired
    public ProfileFractionService(ProfileRepository profileRepository, ProfileFractionRepository profileFractionRepository) {
        this.profileRepository = profileRepository;
        this.profileFractionRepository = profileFractionRepository;
    }

    public void insertProfile(ProfileEntity profileEntity)
    {
        profileRepository.save(profileEntity);
    }

    @Override
    public void convertToEntity(List dtoList) throws IOException {
        System.out.println("Here ...");
        List<ProfileFractionDto> profileFractionDtoList = dtoList;
        profileFractionDtoList.forEach(profileFractionDto ->
        {
            ProfileEntity profileEntity;
            Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileFractionDto.getProfile());
            if(profileEntityOptional.isPresent())
            {
                profileEntity = profileEntityOptional.get();
            }
            else
            {
                profileEntity = new ProfileEntity();
                profileEntity.setId(profileFractionDto.getProfile());


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
            profileRepository.save(profileEntity);
        });

    }

//    @Override
//    public void uploadfile(MultipartFile multipartFile) throws IOException {
//        File file = convertMultiPartToFile(multipartFile);
//
//
//        try (Reader reader = new FileReader(file);) {
////            @SuppressWarnings("unchecked")
//            CsvToBean<ProfileFractionDto> csvToBean = new CsvToBeanBuilder<ProfileFractionDto>(reader).withType(ProfileFractionDto.class)
//                    .withIgnoreLeadingWhiteSpace(true).build();
//            List dtoList = csvToBean.parse();
//
//
//        }


}
