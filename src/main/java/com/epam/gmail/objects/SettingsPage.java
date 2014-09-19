package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SettingsPage {
    private final Logger logger = Logger.getLogger(SettingsPage.class);

    private static final String FORWARDING_HYPERLINK = "Forwarding and POP/IMAP";
    private static final String ADD_FORWARDING_PATH = "//input[@value='Add a forwarding address']";
    private static final String FORWARDING_EMAIL_ADDRESS = "//div[contains(text(),'Please enter a new forwarding email address:')]/div/input";
    private static final String PROCEED_BUTTON_PATH = "html/body/form/table/tbody/tr/td/input[3]";
    private static final String IFRAME = "//*[@class='ds']";
    private static final String OK_BUTTON = "//button[@name='ok']";
    private static final String FORWARD_COPY_RADIOBUTTON = "(.//td//*[@type='radio' and @name='sx_em'][1])[2]";
    private static final String FILTERS_HYPERLINK = "Filters";
    private static final String SAVE_CHANGES_BUTTON = "//button[@guidedhelpid='save_changes_button']";
    private static final String EXISTING_MAIL_NOT_VERIFIED = "//td/b[contains(text(),'Verify testautomation3d@gmail.com')]";
    private static final String EXISTING_MAIL_NOT_VERIFIED_REMOVE = "//span[@act='removeAddr' and contains(text(),'Remove address')]";
    private static final String EXISTING_MAIL_VERIFIED = "//span[contains(text(),'Forward a copy of incoming mail to')]/select[1]";
    private static final String EXISTING_MAIL_VERIFIED_OPT = "//span[contains(text(),'Forward a copy of incoming mail to')]/select[1]/option";

    @FindBy(linkText = FORWARDING_HYPERLINK)
    private WebElement forwardingHyperlink;
    @FindBy(xpath = ADD_FORWARDING_PATH)
    private WebElement addForwardingAdress;
    @FindBy(xpath = FORWARDING_EMAIL_ADDRESS)
    private WebElement forwardingEmailAddress;
    @FindBy(xpath = PROCEED_BUTTON_PATH)
    private WebElement proceedButton;
    @FindBy(xpath = IFRAME)
    private WebElement iFrame;
    @FindBy(xpath = OK_BUTTON)
    private WebElement okButton;
    @FindBy(xpath = FORWARD_COPY_RADIOBUTTON)
    private WebElement forwardCopyRadiobutton;
    @FindBy(linkText = FILTERS_HYPERLINK)
    private WebElement filtersHyperlink;
    @FindBy(xpath = SAVE_CHANGES_BUTTON)
    private WebElement saveChangesButton;

    private WebDriver driver;

    public SettingsPage() {
        logger.info("Gmail settings page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickForwarding() {
        forwardingHyperlink.click();
    }

    public void clickFilters() {
        filtersHyperlink.click();
    }

    public void mailForwarding(String userMail) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (checkElement(EXISTING_MAIL_NOT_VERIFIED)) {
            WebElement removeExistingMail = driver.findElement(By.xpath(EXISTING_MAIL_NOT_VERIFIED_REMOVE));
            removeExistingMail.click();
            okButton.click();
        }
        if (checkElement(EXISTING_MAIL_VERIFIED_OPT)) {
            Select select = new Select(driver.findElement(By.xpath(EXISTING_MAIL_VERIFIED)));
            WebElement selectE = driver.findElement(By.xpath(EXISTING_MAIL_VERIFIED));
            selectE.click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Forward a copy of incoming mail to')]/select[1]//option[contains(text(),'Remove " + userMail + "')]")));
            try {
                select.selectByVisibleText("Remove " + userMail);
            } catch (NoSuchElementException e) {
                select.selectByVisibleText("Remove " + userMail + " (in use)");
            }
            selectE.sendKeys(Keys.ENTER);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().refresh();
        addForwardingAdressAction(userMail);
    }

    public void addForwardingAdressAction(String username) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_FORWARDING_PATH)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(EXISTING_MAIL_NOT_VERIFIED)));
        addForwardingAdress.click();
        forwardingEmailAddress.sendKeys(username);
        forwardingEmailAddress.sendKeys(Keys.ENTER);
        driver.switchTo().frame(iFrame);
        proceedButton.click();
        driver.switchTo().defaultContent();
        okButton.click();
    }

    public void selectForwardCopyRadiobutton() {
        forwardCopyRadiobutton.click();
    }

    public void clickSaveButton() {
        clickElementJS(saveChangesButton, driver);
    }

    public void clickElementJS(WebElement element, WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public boolean checkElement(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }
}
