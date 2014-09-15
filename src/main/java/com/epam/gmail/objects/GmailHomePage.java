package com.epam.gmail.objects;

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
    private static final String URL = "http://gmail.com";

    @FindBy(xpath = EMAIL)
    private WebElement email;
    @FindBy(xpath = PASSWD)
    private WebElement passw;
    @FindBy(xpath = SIGN_IN)
    private WebElement signIn;
    @FindBy(xpath = PRESISTENT_COOKIE)
    private WebElement presistentCookie;

    private WebDriver driver;

    public GmailHomePage() {
        logger.info("Gmail Home page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    public void insertUsername(String username) {
        email.sendKeys(username);
    }

    public void insertPass(String password) {
        passw.sendKeys(password);
    }

    public void login() {
        signIn.click();
    }

    public void chechkSingedIn(){
        if(presistentCookie.isSelected()){
            presistentCookie.click();
        }
    }
}