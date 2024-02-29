package ru.effectivemobile.keyservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailListener {

    @KafkaListener(topics = "verify-code", groupId = "group-1")
    public void getEmailCode(String data) {
        log.info("received: {}", data);
    }
}
