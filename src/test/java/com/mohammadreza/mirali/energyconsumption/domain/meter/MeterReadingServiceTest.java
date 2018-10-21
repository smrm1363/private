package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationsFactory;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MeterReadingServiceTest {


    private MeterReadingService meterReadingService;
    @Mock
    private MeterRepository meterRepository;
    @Mock
    private MeterReadingRepository meterReadingRepository;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private RepositoryCompletion repositoryCompletion;


    public MeterReadingServiceTest() {
    }


    @Test
    public void insertMeter() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        MeterReadingService spy = spy(meterReadingService);
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(getPreperedMeter());
        doNothing().when(spy).saveMeterList(meterEntityList);
        meterReadingService.insertMeter(getPreperedMeter());

    }

    @Test
    public void convertToEntity() throws Exception {
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        MeterReadingService spy = spy(meterReadingService);
        doNothing().when(spy).convertToEntity(getPreperedDto());

    }


@Test
    public void getEntityListFromDtoList() throws Exception{
        meterRepository = mock(MeterRepository.class);
        profileRepository = mock(ProfileRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(getPreperedMeter()));
        when(profileRepository.findById(getPreperedMeter().getProfileEntity().getId())).thenReturn(java.util.Optional.ofNullable(getPreperedProfile()));
        List<MeterEntity> meterEntities = meterReadingService.getEntityListFromDtoList(getPreperedDto());
        assertTrue(meterEntities.size()==2 );

    }

    @Test
    public void deleteMeter() throws Exception {
        meterRepository = mock(MeterRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        doNothing().when(meterRepository).deleteById(getPreperedMeter().getId());
        meterReadingService.deleteMeter(getPreperedMeter().getId());
    }

    @Test
    public void findMeterById() throws Exception {
        meterRepository = mock(MeterRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(getPreperedMeter()));
        MeterEntity result = meterReadingService.findMeterById(getPreperedMeter().getId());
        assertTrue(result.equals(getPreperedMeter()));
    }

    @Test
    public void loadConsumption() throws Exception {

        meterReadingRepository = mock(MeterReadingRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterReadingEntity> meterReadingEntityList = getPreperedMeter().getMeterReadingEntityList();
        when(meterReadingRepository.findByMeterEntityIdAndMonth(getPreperedMeter().getId(),getPreperedMeter().getMeterReadingEntityList().get(0).getMonth()))
                .thenReturn(meterReadingEntityList);
        Double result = meterReadingService.loadConsumption(getPreperedMeter().getId(),getPreperedMeter().getMeterReadingEntityList().get(0).getMonth().toString());
        assertTrue(result.equals(10.0));
    }

    @Test
    public void saveMeterList() throws Exception {

        repositoryCompletion = mock(RepositoryCompletion.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(getPreperedMeter());
        doNothing().when(repositoryCompletion).saveEntityListWithValidation(meterEntityList,meterRepository,"meter.reading.validations");
        meterReadingService.saveMeterList(meterEntityList);

    }

    @Test
    public void getColumnMapping() throws Exception {

        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        Map<String,String> columnMapping = meterReadingService.getColumnMapping();
        assertTrue(columnMapping.get("MeterID").equals("meterID"));
        assertTrue(columnMapping.get("Meter reading").equals("meterReading"));
    }

    MeterEntity getPreperedMeter()
    {
        MeterEntity meterEntity = new MeterEntity();

        meterEntity.setId("001");
        meterEntity.setProfileEntity(getPreperedProfile());
        MeterReadingEntity meterReadingEntity1 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity2 = new MeterReadingEntity(meterEntity, MonthEnum.FEB,10.0);
        MeterReadingEntity meterReadingEntity3 = new MeterReadingEntity(meterEntity, MonthEnum.MAR,10.0);
        MeterReadingEntity meterReadingEntity4 = new MeterReadingEntity(meterEntity, MonthEnum.APR,10.0);
        MeterReadingEntity meterReadingEntity5 = new MeterReadingEntity(meterEntity, MonthEnum.AUG,10.0);
        MeterReadingEntity meterReadingEntity6 = new MeterReadingEntity(meterEntity, MonthEnum.DEC,10.0);
        MeterReadingEntity meterReadingEntity7 = new MeterReadingEntity(meterEntity, MonthEnum.JUL,10.0);
        MeterReadingEntity meterReadingEntity8 = new MeterReadingEntity(meterEntity, MonthEnum.MAY,10.0);
        MeterReadingEntity meterReadingEntity9 = new MeterReadingEntity(meterEntity, MonthEnum.NOV,10.0);
        MeterReadingEntity meterReadingEntity10 = new MeterReadingEntity(meterEntity, MonthEnum.SEP,20.0);
        MeterReadingEntity meterReadingEntity11 = new MeterReadingEntity(meterEntity, MonthEnum.OCT,20.0);
        MeterReadingEntity meterReadingEntity12 = new MeterReadingEntity(meterEntity, MonthEnum.JUN,20.0);
        meterReadingEntity1.setConsumtion(10.0);
        meterEntity.setMeterReadingEntityList(new ArrayList<>());
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity1);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity2);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity3);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity4);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity5);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity6);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity7);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity8);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity9);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity10);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity11);
        meterEntity.getMeterReadingEntityList().add(meterReadingEntity12);
        return meterEntity;
    }
    List getPreperedDto()
    {
        List<MeterReadingDto> meterReadingDtoList = new ArrayList<>();
        meterReadingDtoList.add(new MeterReadingDto("001","A","JAN",10.0));
        meterReadingDtoList.add(new MeterReadingDto("002","A","NOV",10.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","FEB",10.0));
        meterReadingDtoList.add(new MeterReadingDto("001","A","OCT",10.0));
        return meterReadingDtoList;
    }


    ProfileEntity getPreperedProfile()
    {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId("A");
        return profileEntity;
    }

}