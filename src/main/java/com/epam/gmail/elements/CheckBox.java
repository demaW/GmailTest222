package com.epam.gmail.elements;

/**
 * Created by volod on 05-Mar-17.
 */
public interface CheckBox extends Element {
    void toggle();
    void check();
    void uncheck();
    boolean isChecked();
}
