package com.feng.dubbo.primary;

import com.feng.dubbo.primary.api.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {




    public static void main(String[] args) {
        //dubbo://192.168.0.105:20880/com.feng.dubbo.primary.api.PayService
       // PayService payService = null;

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/application.xml"});
        classPathXmlApplicationContext.start();
        PayService payService = (PayService) classPathXmlApplicationContext.getBean("payService");

        String info = payService.pay("test");

        System.out.println(info);
    }
}
