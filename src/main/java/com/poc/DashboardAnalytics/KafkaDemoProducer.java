package com.poc.DashboardAnalytics;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;

/**
 * Created by asingh on 9/11/19.
 */
public class KafkaDemoProducer implements Runnable {

    protected String topicName;
    Producer<String,String> producer;
    public long counter = 0l;
    protected String producerId = null;

    public KafkaDemoProducer(String topicName,String producerId){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092,localhost:9093,localhost:9094");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<String,String>(properties);
        this.topicName = topicName;
        this.producerId = producerId;
    }

    public void run(){
        try {
            while (true){
                counter = counter + 1;
                System.out.println("***********Publishing Message to topic****************");
                System.out.println("Producer Record Key : "+counter + "#" + this.producerId);
                producer.send(new ProducerRecord<String, String>(this.topicName,"Key of Topic Message "+counter+"#"+this.producerId,"Key of Topic Message "+counter));
                System.out.println("######################################");
                Thread.sleep(10000l);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        producer.close();

    }
}
