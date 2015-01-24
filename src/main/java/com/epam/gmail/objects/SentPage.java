package com.epam.gmail.objects;

import com.epam.gmail.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SentPage {

    private WebDriver driver;

    public SentPage(){
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }
    
}
