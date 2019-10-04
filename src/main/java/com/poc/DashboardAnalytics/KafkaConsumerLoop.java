package com.poc.DashboardAnalytics;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by asingh on 1/19/19.
 */
public class KafkaConsumerLoop implements Runnable {
    private  KafkaConsumer<String,String> kafkaConsumer = null;
    private  String consumerGroup =null;
    private  List<String> topicList = null;
    private  String id = null;

    public KafkaConsumerLoop(String consumerGroup,List<String> topicList, String id){
        this.topicList = topicList;
        this.consumerGroup = consumerGroup;
        this.id = id;
        Properties prop = new Properties();
        prop.put("bootstrap.servers","amw1zk1.stage.i.spireon.com:9092");
        prop.put("group.id","demo-group");
        prop.put("key.deserializer", StringDeserializer.class.getName());
        prop.put("value.deserializer", StringDeserializer.class.getName());
        this.kafkaConsumer = new KafkaConsumer<String, String>(prop);
    }
    @Override
    public void run() {
        try{
            kafkaConsumer.subscribe(topicList);
            while(true){
                ConsumerRecords<String,String> records = kafkaConsumer.poll(1000);
                for(ConsumerRecord<String,String> record:records){
                    System.out.println("Partition:"+record.partition());
                    System.out.println("Offset:"+record.offset());
                    System.out.println("value:"+record.value());
                }

            }
        }catch (WakeupException e){

        }finally {
            kafkaConsumer.close();
        }
    }

    public void shutdown(){
        kafkaConsumer.wakeup();
    }
}
