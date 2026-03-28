package io.conduktor.demos.kafka.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikiMediaChangeHandler implements EventHandler {
    KafkaProducer<String, String> kafkaProducer;
    String topic;
    private final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangeHandler.class.getSimpleName());

    public WikiMediaChangeHandler(KafkaProducer<String, String> kafkaProducer, String topic){
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    @Override
    public void onOpen() {
        //nothing here
    }

    @Override
    public void onClosed() {
        //we are closing reading from the stream
        kafkaProducer.close();
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent)  {
        LOGGER.info(messageEvent.getData());
        //stream has received a message coming from http stream
        //now we will send it asynchronously to kafka producer
        kafkaProducer.send(new ProducerRecord<>(topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String s) {
        //nothing here
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Error in stream reading", throwable);
    }
}
