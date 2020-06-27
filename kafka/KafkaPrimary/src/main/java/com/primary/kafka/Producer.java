package com.primary.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Producer extends  Thread{
    //kafka api
    private KafkaProducer<String,String> producer;

    //
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

    @Override
    public void run(){
        int num = 0;
        String msg = "primary kafka send message!"+num;
        while(num<5){
            try {
               RecordMetadata recordMetadata =  producer.send(new ProducerRecord<String,String>(topic,msg)).get();
               System.out.println(recordMetadata.offset()+"->"+recordMetadata.topic());
               TimeUnit.SECONDS.sleep(2);
                ++num;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
      //  producer.send(new ProducerRecord<String, String>())
    }

//    public void sendMessage(){
//        String msg = "primary kafka send message!";
//        producer.send(new ProducerRecord<>(topic,"1",msg),((recordMetadata, e) -> {
//            System.out.println(recordMetadata.offset()+"->"+recordMetadata.topic());
//        }));
//    }

    public void callback(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        new Producer("feng").start();
       // sendMessage();
    }
}