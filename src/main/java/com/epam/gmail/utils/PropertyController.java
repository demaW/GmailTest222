package com.epam.gmail.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by volod on 05-Mar-17.
 */
public class PropertyController {
    static final Logger logger = Logger.getLogger(PropertyController.class);

    public static final String WEBRDIVER = "webdriver.driver.version";
    public static final String CHROMEPATH = "webdriver.chrome.driver";

    private static final String PROPS_PATH = "src/main/resources/config.properties";

    private static Properties instance;

    private PropertyController() {

    }

    public static Properties getProp() {
        if (instance == null) {
            instance = loadProperties();
        }
        return instance;
    }

    public static String readProperty(String propertyName) {
        return getProp().getProperty(propertyName);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        Path propFilePath = Paths.get(PROPS_PATH);
        try {
            logger.info("Getting properties from file");
            InputStream inputStream = Files.newInputStream(propFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Config file was now found... " + e.getMessage());
        }
        return properties;
    }
}