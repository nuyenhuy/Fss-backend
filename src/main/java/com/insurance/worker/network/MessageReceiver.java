package com.insurance.worker.network;

import com.insurance.worker.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    private static Logger logger = LoggingUtil.createLogger(MessageReceiver.class);
    private MessageHandler handler;

    public MessageReceiver(MessageHandler handler) {
        this.handler = handler;
    }

    @KafkaListener(topics = "your_topic_name", groupId = "your_group_id")
    public void listen(String message) {
        try {
            logger.info("Received Message: String");
            handler.onNewMessage(message);
        } catch (Exception e) {
            logger.error("Error:onMessage:" + e.getMessage());
        }
    }

    @KafkaListener(topics = "your_realtime_topic_name", groupId = "your_group_id")
    public void listenRealtime(Realtime message) {
        try {
            logger.info("Received Message: Realtime");
            handler.onNewMessage(message);
        } catch (Exception e) {
            logger.error("Error:onMessage:" + e.getMessage());
        }
    }

    @KafkaListener(topics = "your_request_topic_name", groupId = "your_group_id")
    public void listenRequest(Request message) {
        try {
            logger.info("Received Message: Request");
            handler.onNewMessage(message);
        } catch (Exception e) {
            logger.error("Error:onMessage:" + e.getMessage());
        }
    }

    @KafkaListener(topics = "your_response_topic_name", groupId = "your_group_id")
    public void listenResponse(Response message) {
        try {
            logger.info("Received Message: Response");
            handler.onNewMessage(message);
        } catch (Exception e) {
            logger.error("Error:onMessage:" + e.getMessage());
        }
    }
}
