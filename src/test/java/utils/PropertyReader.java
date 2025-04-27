package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final Properties properties = new Properties();
    private static String baseUrl = "";


    static {
        loadProperties();
        setBaseUrl();
    }

    private static void loadProperties() {
        try (FileInputStream file = new FileInputStream("src/config.properties")) {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load or read config file", e);
        }
    }
    private static void setBaseUrl() {
        String environment = System.getProperty("env", properties.getProperty("defaultEnv"));

        switch (environment.toLowerCase()) {
            case "test":
                baseUrl = properties.getProperty("testUrl");
                break;
            case "dev":
                baseUrl = properties.getProperty("devUrl");
                break;
            default:
                throw new IllegalArgumentException("Invalid environment value provided: " + environment);
        }

        System.out.println("Selected environment: " + environment + " with baseUrl: " + baseUrl);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
