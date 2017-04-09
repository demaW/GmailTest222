package com.epam.gmail.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Created by volod on 05-Mar-17.
 */
public class InputImpl extends  ElementImpl implements Input {

    public InputImpl(WebElement element) {
        super(element);
    }

    @Override
    public void sendKeysAndEnter(CharSequence... charSequences) {
        getWrappedElement().sendKeys(charSequences);
        getWrappedElement().sendKeys(Keys.RETURN);
    }

    @Override
    public String getTextValue() {
        return getWrappedElement().getText();
    }

    @Override
    public int getTextLength() {
        return getWrappedElement().getText().length();
    }

    @Override
    public void clearInput() {
        getWrappedElement().clear();
        logger.info("Field cleared in: " + this.getClass().getName());
    }
}
