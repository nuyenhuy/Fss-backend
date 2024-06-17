package com.insurance.worker.network;

public interface NetworkClient {
    void start(MessageHandler handler) throws Exception;
    void stop();
    void subcribeToppic(String topic) throws Exception;
    void unSubcribeToppic(String topic) throws Exception;

    void send(String topic, String message) throws Exception;
    void send(String topic, Realtime message) throws Exception;
    void send(String topic, Request message) throws Exception;
    void send(String topic, Response message) throws Exception;
    void broadcastWorker(String message) throws Exception;
    void broadcastWorker(Realtime message) throws Exception;
    void broadcastWorker(Request message) throws Exception;
    void broadcastWorker(Response message) throws Exception;
}
