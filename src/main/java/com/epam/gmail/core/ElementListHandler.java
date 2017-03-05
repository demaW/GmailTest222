package com.epam.gmail.core;

import com.epam.gmail.elements.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volod on 05-Mar-17.
 */
public class ElementListHandler implements InvocationHandler {
    private final Class<?> wrappingType;
    ElementLocator locator;

    public <T> ElementListHandler(Class<T> wrappingType, ElementLocator locator) {
        this.locator = locator;
        if (!Element.class.isAssignableFrom(wrappingType)) {
            throw new RuntimeException("interface not assignable to Element");
        }
        this.wrappingType = getWrapperClass(wrappingType);
    }

    private <T> Class<?> getWrapperClass(Class<T> wrappingType) {
        return wrappingType.getClass();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        List<Object> wrappedList = new ArrayList<Object>();
        Constructor cons = wrappingType.getConstructor(WebElement.class);
        for (WebElement element : locator.findElements()) {
            Object thing = cons.newInstance(element);
            wrappedList.add(wrappingType.cast(thing));
        }
        try {
            return method.invoke(wrappedList, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
