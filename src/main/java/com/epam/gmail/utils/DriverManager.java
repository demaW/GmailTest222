package com.epam.gmail.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
    static final Logger logger = Logger.getLogger(DriverManager.class);
    private static RemoteWebDriver instance;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (instance == null) {

            switch (PropertyController.readProperty(PropertyController.WEBRDIVER)) {
                case "firefox":
                    instance = new FirefoxDriver();
                    break;
                case "chrome":
                    System.setProperty(PropertyController.CHROMEPATH, PropertyController.getProp().
                            getProperty(PropertyController.CHROMEPATH));
                    instance = new ChromeDriver();
            }
        }
        return instance;
    }

    public static void closeDriver() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}
