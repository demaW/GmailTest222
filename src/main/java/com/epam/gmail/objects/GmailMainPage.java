package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Volodymyr_Demchyk on 9/10/2014.
 */
public class GmailMainPage {
    private final Logger logger = Logger.getLogger(GmailMainPage.class);

    private static final String SETTINGS_IMG_PATH = "//div[contains(@class,'aos')]";
    private static final String SETTINGS_IN_MENU = "//div[@id='ms']";
    private static final String USER_MENU_ARROW_PATH = ".//span[@class='gb_3']";
    private static final String SIGN_OUT = "//a[contains(text(),'Sign out')]";
    private static final String MORE = "//span[contains(text(),'More')]";
    private static final String TRASH = "//a[@title='Trash']";
    private static final String IMPORTANT = "//a[@title='Important']";

    @FindBy(xpath = SETTINGS_IMG_PATH)
    private WebElement settingsImg;
    @FindBy(xpath = SETTINGS_IN_MENU)
    private WebElement settingsInMenu;
    @FindBy(xpath = USER_MENU_ARROW_PATH)
    private WebElement userMenuArrow;
    @FindBy(xpath = SIGN_OUT)
    private WebElement signOut;
    @FindBy(xpath = MORE)
    private WebElement moreButton;
    @FindBy(xpath = TRASH)
    private WebElement trashButton;
    @FindBy(xpath = IMPORTANT)
    private WebElement importantButton;

    private WebDriver driver;

    public GmailMainPage() {
        logger.info("Gmail Main page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void openSettings(){
        waitElement(driver,5,settingsImg);
        settingsImg.click();
        settingsInMenu.click();
    }

    public void logout(String email){
        driver.findElement(By.linkText(email)).click();
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.elementToBeClickable(signOut));
        signOut.click();
    }

    public void clickMore(){
        moreButton.click();
    }

    public void clickTrashButton(){
        trashButton.click();
    }

    public void clickImportantButton(){
        importantButton.click();
    }
    public void waitUntilInbox() {

        ExpectedCondition<Boolean> expectedUrl = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                String currentUrl = webDriver.getCurrentUrl();
                return currentUrl.contains("inbox");
            }
        };
        (new WebDriverWait(driver, 60)).until(expectedUrl);
    }

    public void waitElement(WebDriver driver, int time, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
