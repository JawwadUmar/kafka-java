package io.conduktor.demos.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());


    public static void main(String[] args) {
        LOGGER.info("I am a Kafka Consumer");

        String groupId = "my-java-application";
        String topic = "demo_topic";

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        properties.setProperty("key.deserializer", StringDeserializer.class.getName()); //will need an Avro deserializer in the future
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest"); //earliest, latest, none

        //create a consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        //subscribe to a topic
        kafkaConsumer.subscribe(Arrays.asList(topic));

        //poll for data
        while (true){
            LOGGER.info("Polling");
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord<String, String> consumerRecord: consumerRecords){
                LOGGER.info("Key " + consumerRecord.key() + " , Value: " + consumerRecord.value());
                LOGGER.info("Partition " + consumerRecord.partition() + " ,Offset: " + consumerRecord.offset());
            }
        }
    }
}
