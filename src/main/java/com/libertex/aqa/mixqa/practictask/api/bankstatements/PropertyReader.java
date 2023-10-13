package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public Properties loadConfigProperties() {

        Properties properties = new Properties();

        try (FileInputStream fileInputStreams = new FileInputStream("config.properties")) {
            properties.load(fileInputStreams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
