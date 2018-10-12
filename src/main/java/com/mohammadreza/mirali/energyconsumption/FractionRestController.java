package com.mohammadreza.mirali.energyconsumption;

import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingDto;
import com.mohammadreza.mirali.energyconsumption.domain.meter.MeterReadingService;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

public class FractionRestController {

    private final ProfileFractionService profileFractionService;
    private final MeterReadingService meterReadingService;

    @Autowired
    public FractionRestController(ProfileFractionService profileFractionService, MeterReadingService meterReadingService) {
        this.profileFractionService = profileFractionService;
        this.meterReadingService = meterReadingService;
    }

    @RequestMapping(value = "/uploadProfileFraction", method = RequestMethod.POST)
    public List uploadProfileFraction(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
        System.out.println("......oooiilllll.....");

        profileFractionService.uploadfile(multiPartFile, ProfileFractionDto.class);
        return null;
    }

    @RequestMapping(value = "/uploadMeterReading", method = RequestMethod.POST)
    public List uploadMeterReading(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
        System.out.println("......oooiilllll.....");

        meterReadingService.uploadfile(multiPartFile, MeterReadingDto.class);
        return null;
    }



    @RequestMapping("/test")
    public void test()  {
        System.out.println("......oooiilllll..test...");

    }



}
