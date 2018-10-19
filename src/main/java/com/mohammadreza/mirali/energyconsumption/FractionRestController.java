package com.mohammadreza.mirali.energyconsumption;

import com.mohammadreza.mirali.energyconsumption.domain.common.ValidationException;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class FractionRestController {

    private final ProfileFractionService profileFractionService;


    @Autowired
    public FractionRestController(ProfileFractionService profileFractionService) {
        this.profileFractionService = profileFractionService;

    }

    @RequestMapping(value = "/uploadProfileFraction", method = RequestMethod.POST)
    public void uploadProfileFraction(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException, ValidationException {

        profileFractionService.uploadMultiPartFile(multiPartFile, ProfileFractionDto.class);

    }

    @RequestMapping(value = "/insertProfile", method = RequestMethod.POST)
    public void insertProfile(@RequestBody ProfileEntity profile) throws ValidationException {
         profileFractionService.insertProfile(profile);
    }

    @RequestMapping(value = "/insertProfileFromLocalFile", method = RequestMethod.POST)
    public void insertProfileFromLocalFile(@RequestPart(value = "localFilePath") String localFilePath) throws IOException, ValidationException {
        profileFractionService.uploadOldFile(localFilePath, ProfileFractionDto.class);

    }

    @RequestMapping(value = "/deleteProfile", method = RequestMethod.DELETE)
    public void deleteProfile(String profileId) throws ValidationException {
        profileFractionService.deleteProfile(profileId);
    }

    @RequestMapping(value = "/findProfile", method = RequestMethod.GET)
    public ProfileEntity findProfile(String profileId)
    {
        return profileFractionService.findProfileById(profileId);
    }




}
