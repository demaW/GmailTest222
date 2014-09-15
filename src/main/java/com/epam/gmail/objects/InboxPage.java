package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class InboxPage {

    private static final String MESSAGE = "(.//div[@id=':2']//td[@tabindex='-1']//span[2])[1]";
    private static final String PARTIAL_LINK_VERIFICATION = "https://isolated.mail.google.com/mail/vf";
    private static final String COMPOSE_BUTTON = "//div[@role='button' and contains(text(),'COMPOSE')]";
    private static final String IFRAME = "//iframe[@tabindex=1]";
    private static final String SEND_TO = ".//span[contains(text(),'To')]/../../..//textarea[@name='to']";
    private static final String ATTACH_FILES_BUTTON = "//div[contains(@aria-label,'Attach files')]/div/div/div";
    private static final String SEND_BUTTON = ".//div[@role='button' and contains(text(),'Send')]";
    private static final String SUBJECT_FIELD = "//input[@name='subjectbox']";
    private static final String ATTACHMENT = "//div[contains(@aria-label,'Attachment')]";
    private static final String INBOX_BUTTON = "//a[contains(@title,'Inbox')]";
    private static final String MESSAGE_NOTIFICATION = "//div[contains(text(),'Your message has been sent.')]";
    private static final String MAILS_TABLE = ".//div[@id=':2']//td[@tabindex='-1']//span[1]";

    @FindBy(xpath = MAILS_TABLE)
    private List<WebElement> subjects;
    @FindBy(xpath = MESSAGE)
    private WebElement message;
    @FindBy(partialLinkText = PARTIAL_LINK_VERIFICATION)
    private WebElement verificationLink;
    @FindBy(xpath = COMPOSE_BUTTON)
    private WebElement composeButton;
    @FindBy(xpath = IFRAME)
    private WebElement iFrame;
    @FindBy(xpath = SEND_TO)
    private WebElement sendToTextArea;
    @FindBy(xpath = ATTACH_FILES_BUTTON)
    private WebElement attachFilesButton;
    @FindBy(xpath = SEND_BUTTON)
    private WebElement sendButton;
    @FindBy(xpath = SUBJECT_FIELD)
    private WebElement subjectField;
    @FindBy(xpath = ATTACHMENT)
    private WebElement attachment;
    @FindBy(xpath = INBOX_BUTTON)
    private WebElement inbox;
    @FindBy(xpath = MESSAGE_NOTIFICATION)
    private WebElement messageNotification;

    private WebDriver driver;
    private String parentHandle;
    public InboxPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public String readMessageText() {
        return message.getText();
    }

    public void openMessage(){
        message.click();
    }

    public void clickVerfifcationLink(){
        parentHandle = driver.getWindowHandle();
        verificationLink.click();
    }

    public void closeSuccessWindow(){
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equalsIgnoreCase(parentHandle)){
                driver.switchTo().window(winHandle);
                 driver.close();
            }
        }
        driver.switchTo().window(parentHandle);
    }

    public void clickComposeButton(){
        composeButton.click();
    }

    public void fillSendToField(String sendTo) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(sendToTextArea));
        sendToTextArea.clear();
        sendToTextArea.sendKeys(sendTo);
    }

    public void clickAttachFiles(){
        attachFilesButton.click();
    }

    public void clickSendButton(){
        sendButton.click();
    }
    public void writeSubjectMessage(String subject) {
        subjectField.sendKeys(subject);
    }

    public void attachFile(){
        try {
            Runtime.getRuntime().exec("src/test/resources/gmail.exe");

        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(attachment));
    }

    public void waitMessageSend(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOf(messageNotification));
    }
    public List<WebElement> getSubjects(){
        return subjects;
    }

    public void clickInbox(){
        inbox.click();
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

    public void navigateInbox() {
        inbox.click();
        waitUntilInbox();
    }
}
