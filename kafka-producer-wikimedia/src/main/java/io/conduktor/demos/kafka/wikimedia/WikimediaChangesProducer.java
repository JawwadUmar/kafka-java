package io.conduktor.demos.kafka.wikimedia;

import com.launchdarkly.eventsource.EventSource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.EventHandler;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WikimediaChangesProducer {
    public static void main(String[] args) throws InterruptedException {
        String bootstrapServers = "127.0.0.1:9092";

        //Create Producer Properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        //Set producer properties
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "wikimedia.recentchange";

        EventHandler eventHandler = new WikiMediaChangeHandler(producer, topic);

        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

//        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));


        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request request = chain.request().newBuilder()
                                    .header("User-Agent", "MyApp/1.0 (jawwadumar99@gmail.com)")
                                    .build();
                            return chain.proceed(request);
                        })
                        .build()
                );

        EventSource eventSource = builder.build();

        //start the producer in another thread
        eventSource.start();

        //I am gonna block for 10 minutes while my other thread produce to apache kafka
        TimeUnit.MINUTES.sleep(10);


    }
}
