package com.mohammadreza.mirali.energyconsumption;

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
    @Autowired
    ProfileFractionService profileFractionService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
        System.out.println("......oooiilllll.....");

        profileFractionService.uploadfile(multiPartFile, ProfileFractionDto.class);
        return null;
    }

    @RequestMapping("/test")
    public void test()  {
        System.out.println("......oooiilllll..test...");

    }



}
