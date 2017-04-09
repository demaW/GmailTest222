package com.epam.gmail.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Created by volod on 05-Mar-17.
 */
public interface Element extends WebElement, WrapsElement, Locatable {
    /**
     * Returns true when the inner element is ready to be used.
     *
     * @return boolean true for an initialized WebElement, or false if we were somehow passed a null WebElement.
     */
    boolean elementWired();

}
