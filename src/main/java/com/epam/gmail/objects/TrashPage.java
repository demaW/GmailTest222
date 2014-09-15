package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TrashPage {
    private static final Logger logger = Logger.getLogger(TrashPage.class);

    private static final String MAILS_TABLE = ".//div[@id=':2']//td[@tabindex='-1']//span[1]";

    @FindBy(xpath = MAILS_TABLE)
    private List<WebElement> subjects;

    private final WebDriver driver;

    public TrashPage() {
        logger.info("Gmail settings page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getSubjects(){
        return subjects;
    }
}
