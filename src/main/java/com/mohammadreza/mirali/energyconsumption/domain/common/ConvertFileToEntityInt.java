package com.mohammadreza.mirali.energyconsumption.domain.common;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface ConvertFileToEntityInt {
    public void convertToEntity(List dtoList) throws IOException, ValidationException;
    public Map<String, String> getColumnMapping();



    default File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    public default <T> void uploadMultiPartFile(MultipartFile multipartFile,Class<T> t) throws IOException, ValidationException {
        File file = convertMultiPartToFile(multipartFile);
        uploadFile(file,t);


        }

    public default <T> void uploadOldFile(String filePath,Class<T> t) throws IOException, ValidationException {
        File file = new File(filePath);
        try{
            uploadFile(file,t);
        }
        catch (ValidationException e)
        {
            /**
             * Write Error log file
             */
            Instant instant = Instant.now();
            long timeStampSeconds = instant.getEpochSecond();
            Path path = Paths.get(file.getParent()+"/"+timeStampSeconds+".log");
            //Use try-with-resource to get auto-closeable writer instance
            try (BufferedWriter writer = Files.newBufferedWriter(path))
            {
                writer.write(e.getMessage());
            }
            throw e;
        }
        /**
         * File processed without any problem, thus delete the file
         */
        file.delete();

    }

    public default <T> void uploadFile(File file,Class<T> t) throws IOException, ValidationException {


        List<T> dtoList = convertFileToDto(new FileReader(file),t);
        convertToEntity(dtoList);


    }
    public default <T> List<T> convertFileToDto(Reader reader,Class<T> t) throws IOException {
        List<T> dtoList;

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType(t)
                    .withIgnoreLeadingWhiteSpace(true).build();


            HeaderColumnNameTranslateMappingStrategy<T> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<T>();
            strategy.setType((Class<? extends T>) t);
            strategy.setColumnMapping(getColumnMapping());

            csvToBean.setMappingStrategy(strategy);
            dtoList = csvToBean.parse();


        return dtoList;

    }


}