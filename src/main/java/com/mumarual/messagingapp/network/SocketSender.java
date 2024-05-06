package com.mumarual.messagingapp.network;

import com.mumarual.messagingapp.model.SocketData;

public interface SocketSender {
    //send by recTopic
    void sendTo(SocketData data) throws Exception;
    //send by topic
    void sendToTopic(String topic, SocketData data);
}
