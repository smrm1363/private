package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.common.MonthEnum;
import com.mohammadreza.mirali.energyconsumption.domain.common.RepositoryCompletion;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationsFactory;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MeterReadingServiceTest {

    @Mock
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

    }

    @Test
    public void deleteMeter() throws Exception {
    }

    @Test
    public void findMeterById() throws Exception {
    }

    @Test
    public void loadConsumption() throws Exception {
    }

    @Test
    public void saveMeterList() throws Exception {
    }

    @Test
    public void getColumnMapping() throws Exception {
    }

    MeterEntity getPreperedMeter()
    {
        MeterEntity meterEntity = new MeterEntity();
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId("A");
        meterEntity.setId("001");
        meterEntity.setProfileEntity(profileEntity);
        MeterReadingEntity meterReadingEntity1 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity2 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity3 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity4 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity5 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity6 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity7 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity8 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity9 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,10.0);
        MeterReadingEntity meterReadingEntity10 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,20.0);
        MeterReadingEntity meterReadingEntity11 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,20.0);
        MeterReadingEntity meterReadingEntity12 = new MeterReadingEntity(meterEntity, MonthEnum.JAN,20.0);
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

}