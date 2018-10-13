package com.mohammadreza.mirali.energyconsumption.domain;

import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

public interface ConvertFileToEntityInt {
    public void convertToEntity(List dtoList) throws IOException;
    public Map<String, String> getColumnMapping();



    default File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    public default <T> void uploadfile(MultipartFile multipartFile,T t) throws IOException {
        File file = convertMultiPartToFile(multipartFile);

        try (Reader reader = new FileReader(file);) {
//            @SuppressWarnings("unchecked")
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType((Class<? extends T>) t)
                    .withIgnoreLeadingWhiteSpace(true).build();


            HeaderColumnNameTranslateMappingStrategy<T> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<T>();
            strategy.setType((Class<? extends T>) t);
            strategy.setColumnMapping(getColumnMapping());

            csvToBean.setMappingStrategy(strategy);
            List dtoList = csvToBean.parse();
            convertToEntity(dtoList);
        }


    }
}