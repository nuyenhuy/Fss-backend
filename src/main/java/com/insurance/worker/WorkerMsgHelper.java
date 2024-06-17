package com.insurance.worker;

import com.insurance.worker.network.Realtime;
import com.insurance.worker.network.Request;
import com.insurance.worker.network.Response;

public interface WorkerMsgHelper {

    void send(String topic, String message) throws Exception;
    void send(String topic, Realtime message) throws Exception;
    void send(String topic, Request message) throws Exception;
    void send(String topic, Response message) throws Exception;

    void broadcastWorker(String message) throws Exception;
    void broadcastWorker(Realtime message) throws Exception;
    void broadcastWorker(Request message) throws Exception;
    void broadcastWorker(Response message) throws Exception;
}
