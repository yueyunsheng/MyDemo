package com.feng.springboot.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {

    @KafkaListener(topics = {"feng"})
    public void listener(ConsumerRecord record){
        Optional msg = Optional.ofNullable(record.value());

        if(msg.isPresent()){

            System.out.println(msg.get());
        }
    }



}
