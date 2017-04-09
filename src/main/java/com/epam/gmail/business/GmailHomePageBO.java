package com.epam.gmail.business;

import com.epam.gmail.objects.GmailHomePage;
import com.epam.gmail.utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class GmailHomePageBO {
    private static final String URL = "http://gmail.com";
    private GmailHomePage gmailHomePage;

    public void login(String username, String password) {
        gmailHomePage = GmailHomePage.initialize(DriverManager.getDriver());
        DriverManager.getDriver().get(URL);
        gmailHomePage.insertUsername(username);
        gmailHomePage.pressNextButton();
        gmailHomePage.insertPass(password);
        gmailHomePage.checkSingedIn();
        gmailHomePage.login();
    }
}
