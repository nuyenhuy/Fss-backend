package com.insurance.worker.network;

import com.insurance.worker.model.SocketData;

public interface SocketSender {
    //send by recTopic
    void sendTo(SocketData data) throws Exception;
    //send by topic
    void sendToTopic(String topic, SocketData data);
}
