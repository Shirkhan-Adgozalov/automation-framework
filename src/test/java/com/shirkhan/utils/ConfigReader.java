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
    
    /**
     * Get browser property with environment-aware fallback
     * In CI/CD environments (Linux), Safari is not available, so fallback to Chrome
     */
    public static String getBrowser() {
        String browser = getProperty("browser", "chrome");

        // Check if running in CI/CD environment (Linux)
        String os = System.getProperty("os.name", "").toLowerCase();
        boolean isLinux = os.contains("linux");
        boolean isCi = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;

        // If Safari is selected but we're in CI/CD (Linux), fallback to Chrome
        if ("safari".equalsIgnoreCase(browser) && (isLinux || isCi)) {
            System.out.println("WARNING: Safari not available in CI/CD environment. Falling back to Chrome.");
            return "chrome";
        }

        return browser;
    }

    public static void reloadProperties() {
        loadProperties();
    }
}