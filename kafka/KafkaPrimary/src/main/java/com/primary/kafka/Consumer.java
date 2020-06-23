package com.primary.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private KafkaConsumer<String,String> consumer;

    private String topic;

    public Consumer(String topic) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "106.53.66.20:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "primary-conumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumer = new KafkaConsumer<String, String>(properties);
        this.topic = topic;

    }

    public void getMessage(){

        consumer.subscribe(Collections.singleton(this.topic));
        ConsumerRecords<String, String> consumerRecord = consumer.poll(Duration.ofSeconds(1));
        consumerRecord.forEach(record->{
            System.out.println(record.key()+"->"+record.value()+"->"+record.offset());
        });
    }

}
