package com.poc.DashboardAnalytics;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;

import java.util.Properties;
import java.util.List;

/**
 * Created by asingh on 9/6/19.
 */
public class KafkaDemoConsumer implements Runnable {



    public String consumerGroup;
    public List<String> kafkaTopics;
    public KafkaConsumer<String,String> kafkaConsumer;

    public KafkaDemoConsumer(String consumerGroup, List<String> kafkaTopics){
        this.kafkaTopics = kafkaTopics;
        this.consumerGroup = consumerGroup;
        Properties prop = new Properties();
        prop.put("bootstrap.servers","localhost:9092,localhost:9093,localhost:9094");
        prop.put("group.id","DemoGroupLatest");
        prop.put("auto.offset.reset","latest");
        prop.put("key.deserializer", StringDeserializer.class.getName());
        prop.put("value.deserializer", StringDeserializer.class.getName());
        this.kafkaConsumer = new KafkaConsumer<String, String>(prop);
    }

    public void run(){
        org.apache.log4j.Logger.getLogger("org").setLevel(Level.ERROR);
        org.apache.log4j.Logger.getLogger("akka").setLevel(Level.ERROR);
        org.apache.log4j.Logger.getLogger("kafka").setLevel(Level.ERROR);
        try {
            kafkaConsumer.subscribe(this.kafkaTopics);
            while (true){
                System.out.println("***********Consuming Message from topic****************");
                ConsumerRecords<String,String> consRecords = kafkaConsumer.poll(1);
                for(ConsumerRecord<String,String> record:consRecords){
                    System.out.println("Consumer Record Offset : "+record.offset()+ " Consumer Record Key :"+ record.key() + " Consumer Record Value :"+ record.value()+" Partition :"+record.partition());
                }
                System.out.println("######################################");
                Thread.sleep(10000l);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            kafkaConsumer.close();
    }
}
