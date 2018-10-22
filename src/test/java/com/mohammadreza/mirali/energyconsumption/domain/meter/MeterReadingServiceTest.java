package com.mohammadreza.mirali.energyconsumption.domain.meter;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
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
        meterEntityList.add(TestCaseData.getPreperedMeter());
        doNothing().when(spy).saveMeterList(meterEntityList);
        meterReadingService.insertMeter(TestCaseData.getPreperedMeter());

    }

    @Test
    public void convertToEntity() throws Exception {
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        MeterReadingService spy = spy(meterReadingService);
        doNothing().when(spy).convertToEntity(TestCaseData.getPreperedDtoMeterReading());

    }


@Test
    public void getEntityListFromDtoList() throws Exception{
        meterRepository = mock(MeterRepository.class);
        profileRepository = mock(ProfileRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(TestCaseData.getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedMeter()));
        when(profileRepository.findById(TestCaseData.getPreperedMeter().getProfileEntity().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedProfile()));
        List<MeterEntity> meterEntities = meterReadingService.getEntityListFromDtoList(TestCaseData.getPreperedDtoMeterReading());
        assertTrue(meterEntities.size()>0 );

    }

    @Test
    public void deleteMeter() throws Exception {
        meterRepository = mock(MeterRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        doNothing().when(meterRepository).deleteById(TestCaseData.getPreperedMeter().getId());
        meterReadingService.deleteMeter(TestCaseData.getPreperedMeter().getId());
    }

    @Test
    public void findMeterById() throws Exception {
        meterRepository = mock(MeterRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        when(meterRepository.findById(TestCaseData.getPreperedMeter().getId())).thenReturn(java.util.Optional.ofNullable(TestCaseData.getPreperedMeter()));
        MeterEntity result = meterReadingService.findMeterById(TestCaseData.getPreperedMeter().getId());
        assertTrue(result.equals(TestCaseData.getPreperedMeter()));
    }

    @Test
    public void loadConsumption() throws Exception {

        meterReadingRepository = mock(MeterReadingRepository.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterReadingEntity> meterReadingEntityList = TestCaseData.getPreperedMeter().getMeterReadingEntityList();
        when(meterReadingRepository.findByMeterEntityIdAndMonth(TestCaseData.getPreperedMeter().getId(),TestCaseData.getPreperedMeter().getMeterReadingEntityList().get(0).getMonth()))
                .thenReturn(meterReadingEntityList);
        Double result = meterReadingService.loadConsumption(TestCaseData.getPreperedMeter().getId(),TestCaseData.getPreperedMeter().getMeterReadingEntityList().get(0).getMonth().toString());
        assertTrue(result.equals(10.0));
    }

    @Test
    public void saveMeterList() throws Exception {

        repositoryCompletion = mock(RepositoryCompletion.class);
        MeterReadingService meterReadingService = new MeterReadingService(meterRepository,meterReadingRepository,profileRepository,repositoryCompletion);
        List<MeterEntity> meterEntityList = new ArrayList<>();
        meterEntityList.add(TestCaseData.getPreperedMeter());
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

}