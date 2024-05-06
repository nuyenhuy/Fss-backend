package com.mumarual.messagingapp.broker;

import com.mumarual.messagingapp.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    @Autowired
    public Sender(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, ChatMessage message) {
        kafkaTemplate.send(topic, message);
    }
}