package io.conduktor.demos.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoKeys.class.getSimpleName());
    public static void main(String[] args) {
        LOGGER.info("I am a Kafka Producer");

        //Create Producer Properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        //Set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("batch.size", "400"); //by default, it is 16kb

        //Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        
        for(int j = 0; j<2; j++){
            for (int i = 0; i<10; i++){
                String topic = "demo_topic";
                String key = "id" + i;
                String value = "Hello World" + i;

                //Create a Producer Record
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        //executed everytime a record is successfully sent or exception thrown
                        if(e == null){
                            //the record was successfully sent
                            LOGGER.info("Received new Metadata \n" +
                                    "Topic: " + recordMetadata.topic() + "\n" +
                                    "Key: " + key + "|" + "Partition: " + recordMetadata.partition() + "\n" +
                                    "Offset: " + recordMetadata.offset() + "\n" +
                                    "Timestamp: " + recordMetadata.timestamp() + "\n"
                            );
                        }
                        else{
                            LOGGER.error("Error while producing", e);
                        }
                    }
                });

            }

            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }

        

        //Tells the producer to send all data and block until done ... synchronous operation
        producer.flush();

        //Flush and close the producer
        producer.close(); //will first call producer.flush() but better to explicitly mention

    }
}
