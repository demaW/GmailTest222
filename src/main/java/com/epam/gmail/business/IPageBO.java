package com.epam.gmail.business;

import com.epam.gmail.utils.DriverManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class IPageBO {

    protected boolean checkEmailPresence(List<WebElement> elements,String expectedSubject){
        boolean result = false;
        for(int i=0; i<12;){
        for (WebElement webElement : elements) {
            if (webElement.getText().contains(expectedSubject))
                return true;
        }
            DriverManager.getDriver().navigate().refresh();
        }
        return result;
    }
}
