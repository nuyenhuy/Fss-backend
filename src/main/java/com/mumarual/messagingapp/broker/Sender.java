package com.mumarual.messagingapp.broker;

import com.mumarual.messagingapp.message.ChatMessage;
import com.mumarual.messagingapp.message.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public Sender(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, ChatMessage message) {
        kafkaTemplate.send(topic, message);
    }
}