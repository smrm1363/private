package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by mmirali on 21/10/2018.
 */
public class ProfileFractionServiceTest {

    @Mock
    private  ProfileRepository profileRepository;
    @Mock
    private RepositoryCompletion repositoryCompletion;
    @Mock
    private MeterRepository meterRepository;

    @Test
    public void insertProfile() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        ProfileFractionService spy = Mockito.spy(profileFractionService);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(getPreperedProfile());
        doNothing().when(spy).saveProfileList(profileEntityList);
        spy.insertProfile(getPreperedProfile());

    }

    @Test
    public void convertToEntity() throws Exception {
        ProfileFractionService profileFractionService = mock(ProfileFractionService.class);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(getPreperedProfile());
        when(profileFractionService.getEntityListFromDtoList(getPreperedDto())).thenReturn((profileEntityList));
        profileFractionService.convertToEntity(getPreperedDto());


    }

    @Test
    public void getEntityListFromDtoList() throws Exception {


        profileRepository = mock(ProfileRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(getPreperedProfile()));
        List<ProfileEntity> profileEntityList = profileFractionService.getEntityListFromDtoList(getPreperedDto());
        assertTrue(profileEntityList.size()==2 );
    }

    @Test
    public void deleteProfile() throws Exception {
        profileRepository = mock(ProfileRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        doNothing().when(profileRepository).deleteById(getPreperedProfile().getId());
        profileFractionService.deleteProfile(getPreperedProfile().getId());
    }

    @Test
    public void findProfileById() throws Exception {
        profileRepository = mock(ProfileRepository.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        when(profileRepository.findById(getPreperedProfile().getId())).thenReturn(java.util.Optional.ofNullable(getPreperedProfile()));
        ProfileEntity result = profileFractionService.findProfileById(getPreperedProfile().getId());
        assertTrue(result.equals(getPreperedProfile()));
    }

    @Test
    public void saveProfileList() throws Exception {
        repositoryCompletion = mock(RepositoryCompletion.class);
        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(getPreperedProfile());
        doNothing().when(repositoryCompletion).saveEntityListWithValidation(profileEntityList,meterRepository,"profile.fraction.validation");
        profileFractionService.saveProfileList(profileEntityList);
    }

    @Test
    public void getColumnMapping() throws Exception {

        ProfileFractionService profileFractionService = new ProfileFractionService(profileRepository,repositoryCompletion,meterRepository);
        Map<String,String> columnMapping = profileFractionService.getColumnMapping();
        assertTrue(columnMapping.get("Month").equals("month"));
        assertTrue(columnMapping.get("Fraction").equals("fraction"));
    }

    ProfileEntity getPreperedProfile()
    {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setId("A");
//        meterEntity.setProfileEntity(getPreperedProfile());
        ProfileFractionEntity profileFractionEntity1 = new ProfileFractionEntity(profileEntity, MonthEnum.JAN,0.1);
        ProfileFractionEntity profileFractionEntity2 = new ProfileFractionEntity(profileEntity, MonthEnum.FEB,0.1);
        ProfileFractionEntity profileFractionEntity3 = new ProfileFractionEntity(profileEntity, MonthEnum.MAR,0.1);
        ProfileFractionEntity profileFractionEntity4 = new ProfileFractionEntity(profileEntity, MonthEnum.APR,0.1);
        ProfileFractionEntity profileFractionEntity5 = new ProfileFractionEntity(profileEntity, MonthEnum.AUG,0.1);
        ProfileFractionEntity profileFractionEntity6 = new ProfileFractionEntity(profileEntity, MonthEnum.DEC,0.1);
        ProfileFractionEntity profileFractionEntity7 = new ProfileFractionEntity(profileEntity, MonthEnum.JUL,0.1);
        ProfileFractionEntity profileFractionEntity8 = new ProfileFractionEntity(profileEntity, MonthEnum.MAY,0.1);
        ProfileFractionEntity profileFractionEntity9 = new ProfileFractionEntity(profileEntity, MonthEnum.NOV,0.1);
        ProfileFractionEntity profileFractionEntity10 = new ProfileFractionEntity(profileEntity, MonthEnum.SEP,0.1);
        ProfileFractionEntity profileFractionEntity11 = new ProfileFractionEntity(profileEntity, MonthEnum.OCT,0.0);
        ProfileFractionEntity profileFractionEntity12 = new ProfileFractionEntity(profileEntity, MonthEnum.JUN,0.0);
        profileEntity.setProfileFractionEntityList(new ArrayList<>());
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity1);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity2);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity3);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity4);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity5);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity6);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity7);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity8);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity9);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity10);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity11);
        profileEntity.getProfileFractionEntityList().add(profileFractionEntity12);
        return profileEntity;
    }

    List getPreperedDto()
    {
        List<ProfileFractionDto> meterReadingDtoList = new ArrayList<>();
        meterReadingDtoList.add(new ProfileFractionDto("JAN","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("NOV","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("FEB","A",0.1));
        meterReadingDtoList.add(new ProfileFractionDto("OCT","B",0.1));
        return meterReadingDtoList;
    }
}