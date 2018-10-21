package com.mohammadreza.mirali.energyconsumption.domain.profile;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void convertToEntity() throws Exception {
        profileFractionService.convertToEntity(TestCaseData.getPreperedProfileFractionDto());
    }

    @Test
    public void getEntityListFromDtoList() throws Exception {
    }

    @Test
    public void saveProfileList() throws Exception {
    }

    @Test
    public void getColumnMapping() throws Exception {
    }

}