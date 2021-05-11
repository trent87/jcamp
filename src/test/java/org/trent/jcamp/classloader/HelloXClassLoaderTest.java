package org.trent.jcamp.classloader;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class HelloXClassLoaderTest {

    @Test
    void findClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloXClassLoader helloXClassLoader = new HelloXClassLoader("Hello.xlass");
        Class<?> clazz = helloXClassLoader.findClass("Hello");
        Object instance = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("hello");
        method.invoke(instance);
    }
}