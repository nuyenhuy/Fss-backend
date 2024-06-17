package com.insurance.worker.network;

public interface MessageHandler {

    void onNewMessage(String msg);
    void onNewMessage(Realtime msg);
    void onNewMessage(Request msg);
    void onNewMessage(Response msg);
}
