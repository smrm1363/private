package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import org.assertj.core.util.Files;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mmirali on 20/10/2018.
 */
public class ConvertFileToEntityIntTest {
    @Test
    public void convertMultiPartToFile() throws Exception {
        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class,Mockito.CALLS_REAL_METHODS);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test-file","test.csv","text/plain", "test data".getBytes());
        File file = testedClass.convertMultiPartToFile(mockMultipartFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertTrue(br.readLine().equals("test data"));
    }

    @Test
    public void uploadMultiPartFile() throws Exception {
    }

    @Test
    public void uploadOldFile() throws Exception {
    }

    @Test
    public void convertFileToDto() throws Exception {

        ConvertFileToEntityInt testedClass = Mockito.mock(ConvertFileToEntityInt.class,Mockito.CALLS_REAL_METHODS);
        StringReader reader = new StringReader("Month,Profile,Fraction\n" +
                "JAN,A,0.2\n" +
                "JAN,B,0.18\n" +
                "FEB,A,0.1\n" +
                "MAR,B,0.21\n" +
                "APR,A,0.1\n" +
                "MAY,A,0.1\n" +
                "JUN,A,0.1\n" +
                "JUL,A,0.1\n" +
                "AUG,A,0.1\n" +
                "SEP,A,0.0\n" +
                "OCT,A,0.0\n" +
                "NOV,A,0.1\n" +
                "DEC,A,0.1\n" +
                "MAR,A,0.0");


        List list = testedClass.convertFileToDto(reader,ProfileFractionDto.class);
        assertTrue(((ProfileFractionDto)(ProfileFractionDto)list.get(0)).getMonth().equals("JAN"));
        assertTrue(((ProfileFractionDto)(ProfileFractionDto)list.get(0)).getProfile().equals("A"));
        assertTrue(((ProfileFractionDto)(ProfileFractionDto)list.get(0)).getFraction().equals(0.2));



    }

}