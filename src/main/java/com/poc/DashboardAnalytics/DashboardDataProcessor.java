package com.poc.DashboardAnalytics;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.common.serialization.Serdes;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by asingh on 1/15/19.
 */
public class DashboardDataProcessor {

    public static void main(String[] args) throws Exception{
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dashboard-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.stage.i.spireon.com:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //StreamsConfig streamsConfig = new StreamsConfig(config);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream("DeviceLocationChangeTopic");


        KafkaStreams kfStreams = new KafkaStreams(builder.build(),config);
        kfStreams.start();
        stream.foreach((key,value)->{
            System.out.println("Key ::::: "+key);
            System.out.println("Value ::::: "+value);
        });

        //Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
