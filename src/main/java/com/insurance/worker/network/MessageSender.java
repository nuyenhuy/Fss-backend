package com.insurance.worker.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String topic, Object message) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, jsonString);
    }
}
