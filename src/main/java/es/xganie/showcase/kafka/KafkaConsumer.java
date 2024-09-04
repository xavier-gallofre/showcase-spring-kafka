package es.xganie.showcase.kafka;

import es.xganie.showcase.service.RecordProcessorService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final RecordProcessorService recordProcessorService;


    public KafkaConsumer(RecordProcessorService recordProcessorService) {
        this.recordProcessorService = recordProcessorService;
    }

    @KafkaListener(topics = "${showcase.kafka.topic}")
    public void consume(ConsumerRecord<String, String> record) {
        log.info("Consume records: {}", record.toString());
        recordProcessorService.process(record.value());
    }

}
