package com.shirkhan.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            } else {
                throw new RuntimeException("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            return null;
        }
        return value;
    }
    
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }
    
    public static void reloadProperties() {
        loadProperties();
    }
}