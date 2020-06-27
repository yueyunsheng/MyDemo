package com.feng.dubbo.paimary;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/application.xml"});
        classPathXmlApplicationContext.start();
        System.in.read();
    }
}
