package com.epam.gmail.elements;

import org.openqa.selenium.WebElement;

/**
 * Created by volod on 05-Mar-17.
 */
public class CheckBoxImpl extends ElementImpl implements CheckBox {

    public CheckBoxImpl(WebElement element) {
        super(element);
    }

    @Override
    public void toggle() {
        getWrappedElement().click();
    }

    @Override
    public void check() {
        if (!isChecked()){
            getWrappedElement().click();
        }
    }

    @Override
    public void uncheck() {
        if (isChecked()){
            getWrappedElement().click();
        }
    }

    @Override
    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
}
