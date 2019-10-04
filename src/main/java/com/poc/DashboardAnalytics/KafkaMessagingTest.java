package com.poc.DashboardAnalytics;


import org.apache.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by asingh on 9/11/19.
 */
public class KafkaMessagingTest {

    public static void main(String[] args){

        org.apache.log4j.Logger.getLogger("org").setLevel(Level.ERROR);
        org.apache.log4j.Logger.getLogger("akka").setLevel(Level.ERROR);
        org.apache.log4j.Logger.getLogger("kafka").setLevel(Level.ERROR);

        ExecutorService prod = Executors.newFixedThreadPool(4);
        ExecutorService cons = Executors.newFixedThreadPool(3);

        ArrayList<String> topicList = new ArrayList<>();
        topicList.add("demo-topic-three-partition");
        Runnable consumer1 = new KafkaDemoConsumer("demo-group",topicList);
        Runnable consumer2 = new KafkaDemoConsumer("demo-group",topicList);
        Runnable consumer3 = new KafkaDemoConsumer("demo-group",topicList);

        Runnable producer1 = new KafkaDemoProducer("demo-topic-three-partition","Producer-01");
        Runnable producer2 = new KafkaDemoProducer("demo-topic-three-partition","Producer-02");
        Runnable producer3 = new KafkaDemoProducer("demo-topic-three-partition","Producer-03");
        Runnable producer4 = new KafkaDemoProducer("demo-topic-three-partition","Producer-04");
        prod.submit(producer1);
        prod.submit(producer2);
        prod.submit(producer3);
        prod.submit(producer4);

        cons.submit(consumer1);
        cons.submit(consumer2);
        cons.submit(consumer3);

    }
}
