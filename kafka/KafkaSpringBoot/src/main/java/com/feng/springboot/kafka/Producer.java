package com.feng.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(int num){
        kafkaTemplate.send("feng","msgKey","msgDate :"+num);
    }

}
