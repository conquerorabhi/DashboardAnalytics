package com.poc.DashboardAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by asingh on 1/19/19.
 */
public class ConsumerTest {

    public static void main(String[] args){
        ExecutorService exs = Executors.newFixedThreadPool(3);
        List<String> topicList = new ArrayList<String>();
        topicList.add("DeviceLocationChangeTopic");
        for(int index=0;index<3;index++){
            Runnable worker = new KafkaConsumerLoop("analytics-consumer",topicList,index+"");
            exs.submit(worker);
        }

    }
}
