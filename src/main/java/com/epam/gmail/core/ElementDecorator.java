package com.epam.gmail.core;

import com.epam.gmail.elements.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.*;
import java.util.List;

/**
 * Created by volod on 05-Mar-17.
 */
public class ElementDecorator implements FieldDecorator {

    /* factory to use when generating ElementLocator. */
    protected ElementLocatorFactory factory;

    public ElementDecorator(ElementLocatorFactory factory){
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader classLoader, Field field) {
        if(!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))){
            return null;
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null){
            return null;
        }
        Class<?> fieldType = field.getType();
        if (WebElement.class.equals(fieldType)){
            fieldType = Element.class;
        }
        if (WebElement.class.isAssignableFrom(fieldType)){
            return proxyForLocator(classLoader, fieldType, locator);
        } else if (List.class.isAssignableFrom(fieldType)) {
            Class<?> erasureClass = getErasureClass(field);
            return proxyForListLocator(classLoader, erasureClass, locator);
        } else {
            return null;
        }
    }

    protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
        InvocationHandler handler = new ElementHandler(interfaceType, locator);

        T proxy;
        proxy = interfaceType.cast(Proxy.newProxyInstance(
                loader, new Class[]{interfaceType, WebElement.class, WrapsElement.class, Locatable.class}, handler));
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
        InvocationHandler handler = new ElementListHandler(interfaceType, locator);

        List<T> proxy;
        proxy = (List<T>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }

    private boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }
        Class erasureClass = getErasureClass(field);
        if (erasureClass == null || !WebElement.class.isAssignableFrom(erasureClass)) {
            return false;
        }
        if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
            return false;
        }
        return true;
    }

    private Class getErasureClass(Field field) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)){
            return null;
        }
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }
}
