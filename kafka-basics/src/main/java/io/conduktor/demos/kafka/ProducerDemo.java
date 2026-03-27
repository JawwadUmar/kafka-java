package io.conduktor.demos.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());
    public static void main(String[] args) {
        LOGGER.info("Hello word");

        //Create Producer Properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        //Set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //Create a Producer Record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_topic", "hello word");

        //Send data
        producer.send(producerRecord);

        //Tells the producer to send all data and block until done ... synchronous operation
        producer.flush();

        //Flush and close the producer
        producer.close(); //will first call producer.flush() but better to explicitly mention


    }
}
