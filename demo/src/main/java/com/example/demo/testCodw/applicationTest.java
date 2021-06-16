package com.example.demo.testCodw;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class applicationTest implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public applicationTest() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationTest.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入");
        }
    }
}
