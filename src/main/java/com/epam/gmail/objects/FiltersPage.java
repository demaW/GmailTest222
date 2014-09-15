package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FiltersPage {
    private static final Logger logger = Logger.getLogger(FiltersPage.class);

    private static final String CREATE_NEW_FILTER = ".//span[contains(text(),'Create a new filter')]";
    private static final String FROM_INPUT = "//label[contains(text(),'From')]/../../span/input";
    private static final String HAS_ATTACHMENT = "//label[contains(text(),'Has attachment')]/../input";
    private static final String CREATE_FILTER_WITH_SEARCH = "//div[contains(text(),'Create filter with this search')]";
    private static final String DELETE_CHECKBOX = ".//label[contains(text(),'Delete it')]/../input[@type='checkbox']";
    private static final String ALWAYS_MARK_AS_IMPORTANT_CHECKBOX = ".//label[contains(text(),'Always mark it as important')]/../input[@type='checkbox']";
    private static final String CREATE_FILTER_BUTTON = "//div[@role='button' and contains(text(),'Create filter')]";
    private static final String FILTER = ".//span[contains(text(),'from:(test')]";

    @FindBy(xpath = CREATE_NEW_FILTER)
    private WebElement createNewFilter;
    @FindBy(xpath = FROM_INPUT)
    private WebElement fromInput;
    @FindBy (xpath = HAS_ATTACHMENT)
    private WebElement hasAttachmentCheckbox;
    @FindBy(xpath = CREATE_FILTER_WITH_SEARCH)
    private WebElement createFilterWithSerach;
    @FindBy(xpath = DELETE_CHECKBOX)
    private WebElement deleteCheckbox;
    @FindBy (xpath = ALWAYS_MARK_AS_IMPORTANT_CHECKBOX)
    private WebElement alwaysMarkAsImportant;
    @FindBy (xpath = CREATE_FILTER_BUTTON)
    private WebElement createFilterButton;
    @FindBy (xpath = FILTER)
    private WebElement filteOnPage;
    private WebDriver driver;

    public FiltersPage(){
        logger.info("Filters page creating");
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickNewFilter(){
        createNewFilter.click();
    }

    public void setFromInput(String username){
        fromInput.sendKeys(username);
    }

    public void checkHasAttachment(){
       checkCheckbox(hasAttachmentCheckbox);
    }

    public void clickCreateFilter(){
        createFilterWithSerach.click();
    }

    public void checkDeletedCheckbox(){
        checkCheckbox(deleteCheckbox);
    }

    public void checkAlwaysMarkAsImportantCheckbox(){
        checkCheckbox(alwaysMarkAsImportant);
    }

    public void clickCreateFilterButton(){
        createFilterButton.click();
    }

    private void checkCheckbox(WebElement element){
        if (!element.isSelected()){
            element.click();
        }
    }

    public void waitUntilFilter() {

        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return filteOnPage.isDisplayed();
            }
        };
        (new WebDriverWait(driver, 60)).until(expectedCondition);
    }

}
