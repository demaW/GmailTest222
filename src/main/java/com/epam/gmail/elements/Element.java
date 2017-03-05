package com.epam.gmail.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Created by volod on 05-Mar-17.
 */
public interface Element extends WebElement, WrapsElement, Locatable {
    boolean elementWrapped();
}
