package com.epam.gmail.elements;

/**
 * Created by volod on 05-Mar-17.
 */
public interface Input extends Element {
    void sendKeysAndEnter(CharSequence... charSequences);
    String getTextValue();
    int getTextLength();
    void clearInput();
}
