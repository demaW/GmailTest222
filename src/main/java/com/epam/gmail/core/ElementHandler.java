package com.epam.gmail.core;

import com.epam.gmail.elements.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by volod on 05-Mar-17.
 */
public class ElementHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<?> wrappingType;

    public <T> ElementHandler(Class<T> wrappingType, ElementLocator locator) {
        this.locator = locator;
        if (!Element.class.isAssignableFrom(wrappingType)){
            throw new RuntimeException("interface not assignable to Element");
        }
        this.wrappingType = getWrapperClass(wrappingType);
    }

    private <T> Class<?> getWrapperClass(Class<T> wrappingType) {
        return wrappingType.getClass();
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        WebElement element = locator.findElement();

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        Constructor cons = wrappingType.getConstructor(WebElement.class);
        Object thing = cons.newInstance(element);
        try {
            return method.invoke(wrappingType.cast(thing), objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
