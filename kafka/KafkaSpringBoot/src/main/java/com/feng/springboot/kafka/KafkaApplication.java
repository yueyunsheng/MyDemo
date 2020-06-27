package com.feng.springboot.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class,args);
        Producer producer = context.getBean(Producer.class);
        for(int i=0;i<10;i++){
            producer.send(i);;
            TimeUnit.SECONDS.sleep(2);
        }

    }
}
