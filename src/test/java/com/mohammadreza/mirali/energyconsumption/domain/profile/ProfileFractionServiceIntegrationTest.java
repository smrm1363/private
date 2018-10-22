package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileFractionServiceIntegrationTest {

    @Autowired
    ProfileFractionService profileFractionService;
    @Test
    public void crudProfile() throws Exception {
        profileFractionService.insertProfile(TestCaseData.getPreperedProfile());
        ProfileEntity profileEntity = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertEquals(profileEntity.getId(),TestCaseData.getPreperedProfile().getId());
        profileFractionService.deleteProfile(profileEntity.getId());
        profileEntity = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertNull(profileEntity);
    }

    @Test
    @Transactional
    public void convertToEntity() throws Exception {
        profileFractionService.convertToEntity(TestCaseData.getPreperedProfileFractionDto());
        ProfileEntity profileEntity = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertTrue(profileEntity != null);
    }


    @Test
    public void saveProfileList() throws Exception {
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        profileFractionService.saveProfileList(profileEntityList);
        ProfileEntity profileEntity = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertTrue(profileEntity != null);
    }

    @Test(expected = ValidationException.class)
    public void saveProfileListExpectedException() throws Exception {
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        profileEntityList.get(0).getProfileFractionEntityList().get(0).setFraction(2.0);
        profileFractionService.saveProfileList(profileEntityList);
        ProfileEntity profileEntity = profileFractionService.findProfileById(TestCaseData.getPreperedProfile().getId());
        assertTrue(profileEntity != null);
    }



}