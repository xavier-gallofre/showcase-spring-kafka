package es.xganie.showcase.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaConsumerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Value("${showcase.kafka.topic}")
    private String topic;

    @Test
    void sendSomeMessageAndPrintWithoutAssert() throws InterruptedException {
        kafkaTemplate.send(topic, "Hello World");
        Thread.sleep(1000); // Add some sleep to see the message "Hello world" on the log
    }

}