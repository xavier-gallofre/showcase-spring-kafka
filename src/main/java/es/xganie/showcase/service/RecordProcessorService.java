package es.xganie.showcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecordProcessorService {

    private final static Logger log = LoggerFactory.getLogger(RecordProcessorService.class);

    public void process(String recordValue) {
        log.info("Record processed: {}", recordValue);
    }

}
