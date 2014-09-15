package com.epam.gmail.objects;

import com.epam.gmail.business.GmailMainPageBO;
import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ImportantPage {

    private static final Logger logger = Logger.getLogger(ImportantPage.class);

    private static final String MAILS_TABLE = ".//div[@id=':2']//td[@tabindex='-1']//span[1]";
    private static final String REFRESH_BUTTON = ".//div[@data-tooltip='Refresh']";

    @FindBy(xpath = MAILS_TABLE)
    private List<WebElement> subjects;
    @FindBy(xpath = REFRESH_BUTTON)
    private WebElement refreshButton;

    private final WebDriver driver;

    public ImportantPage() {
        logger.info("Important messages page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getSubjects() {
        return subjects;
    }

    public void waitUntilImportant() {
        ExpectedCondition<Boolean> expectedUrl = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                String currentUrl = webDriver.getCurrentUrl();
                return currentUrl.contains("imp");
            }
        };
        (new WebDriverWait(driver, 60)).until(expectedUrl);
    }

    public boolean checkEmailPresenceImportant(List<WebElement> elements,String expectedSubject){
        boolean result = false;
        GmailMainPageBO gmailMainPageBO = new GmailMainPageBO();
        for(int i=0; i<100000;){
            for (WebElement webElement : elements) {
                if (webElement.getText().contains(expectedSubject))
                    return true;
            }
            if (result)
                return result;
            refreshButton.click();
        }
        return result;
    }
}
