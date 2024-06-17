package com.insurance.worker.network;

import com.insurance.worker.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetworkClientImpl implements NetworkClient {
    private static Logger logger = LoggingUtil.createLogger(NetworkClientImpl.class);

    private MessageHandler handler;

    @Autowired
    MessageSender kafkaSender;

    @Override
    public void start(MessageHandler handler) throws Exception {
        this.handler = handler;
        // You may initialize Kafka listeners or perform other initialization tasks here
    }

    @Override
    public void stop() {
        // Implement if needed
    }

    @Override
    public void subcribeToppic(String topic) throws Exception {
        // Subscribe logic if needed
    }

    @Override
    public void unSubcribeToppic(String topic) throws Exception {
        // Unsubscribe logic if needed
    }

    @Override
    public void send(String topic, String message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Realtime message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Request message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Response message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void broadcastWorker(String message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Realtime message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Request message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Response message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }
}
