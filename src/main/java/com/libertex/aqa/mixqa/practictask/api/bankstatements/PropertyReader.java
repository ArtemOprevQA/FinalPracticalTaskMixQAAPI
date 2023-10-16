package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties PROPERTIES = new Properties();

    private PropertyReader() {
    }

    public static void loadConfigProperties() {

            try (FileInputStream fileInputStreams = new FileInputStream("config.properties")) {
                PROPERTIES.load(fileInputStreams);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static String getProperty(String name){
        if (PROPERTIES.isEmpty()){
            loadConfigProperties();
        }
        return PROPERTIES.getProperty(name);
    }
}
