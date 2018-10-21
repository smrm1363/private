package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.mohammadreza.mirali.energyconsumption.domain.TestCaseData;
import com.mohammadreza.mirali.energyconsumption.domain.meter.validation.ConsumptionToleranceValidationTest;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionServiceTest;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileRepository;
import com.mohammadreza.mirali.energyconsumption.domain.profile.validation.SumFractionValidation;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryCompletionTest {
    @Mock
    private ValidationsFactory validationsFactory;
    @Mock
    private ProfileRepository profileRepository;
    @Test
    public void saveEntityListWithValidation() throws Exception {


        validationsFactory = mock(ValidationsFactory.class);
        RepositoryCompletion repositoryCompletion = new RepositoryCompletion(validationsFactory);
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        profileEntityList.add(TestCaseData.getPreperedProfile());
        ProfileRepository profileRepository = mock(ProfileRepository.class);
        List<ValidationRule> validationRuleList = new ArrayList<>();
        validationRuleList.add(new SumFractionValidation());
        when(validationsFactory.getValidationRulesByPropertyName(anyString())).thenReturn(validationRuleList);
        when(profileRepository.saveAll(any())).thenReturn(profileEntityList);
        repositoryCompletion.saveEntityListWithValidation(profileEntityList,profileRepository,"");
    }

}