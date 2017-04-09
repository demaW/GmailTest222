package com.epam.gmail.objects;

import com.epam.gmail.core.ElementFactory;
import com.epam.gmail.elements.Input;
import com.epam.gmail.elements.InputImpl;
import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailHomePage {
    static final Logger logger = Logger.getLogger(GmailHomePage.class);

    private static final String SIGN_IN = ".//*[@id='signIn']";
    private static final String PASSWD = ".//*[@id='Passwd']";
    private static final String EMAIL = ".//*[@id='Email']";
    private static final String PRESISTENT_COOKIE = ".//*[@id='PersistentCookie']";
    private static final String NEXT_BUTTON = "next";

    @FindBy(xpath = EMAIL)
    private Input email;
    @FindBy(xpath = PASSWD)
    private WebElement passw;
    @FindBy(xpath = SIGN_IN)
    private WebElement signIn;
    @FindBy(xpath = PRESISTENT_COOKIE)
    private WebElement presistentCookie;
    @FindBy(id = NEXT_BUTTON)
    private WebElement nextButton;

    private WebDriver driver;

    public static GmailHomePage initialize(WebDriver driver){
        return ElementFactory.initElements(driver, GmailHomePage.class);
    }

    public void insertUsername(String username) {
        email.sendKeysAndEnter(username);
    }

    public void insertPass(String password) {
        passw.sendKeys(password);
    }

    public void login() {
        signIn.click();
    }

    public void checkSingedIn() {
        if (presistentCookie.isSelected()) {
            presistentCookie.click();
        }
    }

    public void pressNextButton() {
        nextButton.click();
    }
}
