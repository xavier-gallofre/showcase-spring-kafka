package es.xganie.showcase.kafka;

import es.xganie.showcase.service.RecordProcessorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaConsumerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    RecordProcessorService recordProcessorService;

    @Value("${showcase.kafka.topic}")
    private String topic;

    @DisplayName("When a message is sent to kafka topic, then the message is process with a service")
    @Test
    void whenMessageIsSentToKafka_thenProcessIsCalledWithMessage() {
        kafkaTemplate.send(topic, "Hello World");
        await()
                .atMost(2, TimeUnit.SECONDS)
                .untilAsserted(() -> verify(recordProcessorService, times(1)).process(anyString()));
    }

}