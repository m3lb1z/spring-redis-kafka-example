package dev.emrx.users.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomTopicListener {

    private static final Logger log = LoggerFactory.getLogger(CustomTopicListener.class);

    @KafkaListener(topics = "custom-topic", groupId = "custom-group")
    public void listen(String message) {
        log.info("Received message: {}", message);
    }

}
