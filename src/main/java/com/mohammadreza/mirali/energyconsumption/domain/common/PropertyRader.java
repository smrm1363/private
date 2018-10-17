package com.mohammadreza.mirali.energyconsumption.domain.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyRader {

    public static String getProperty(String propertyFilePath,String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;

            input = new FileInputStream(propertyFilePath);

            // load a properties file
            prop.load(input);
            return prop.getProperty(key);
    }
    public static String getProperty(String key) throws IOException {

        return getProperty("application.properties",key);
    }
}
