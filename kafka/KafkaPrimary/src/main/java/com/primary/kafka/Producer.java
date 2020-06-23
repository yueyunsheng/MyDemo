package com.primary.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    private KafkaProducer<String,String> producer;
    private String topic;

    public Producer(String topic){

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"106.53.66.20:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"primary-producer");
       // properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;
    }

    public void sendMessage(){
        String msg = "primary kafka send message!";
        producer.send(new ProducerRecord<>(topic,"1",msg),((recordMetadata, e) -> {
            System.out.println(recordMetadata.offset()+"->"+recordMetadata.topic());
        }));
    }

    public void callback(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        new Producer("feng").sendMessage();
       // sendMessage();
    }
}
