package com.insurance.worker;

import com.insurance.worker.network.NetworkClient;
import com.insurance.worker.network.Realtime;
import com.insurance.worker.network.Request;
import com.insurance.worker.network.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerMsgHelperImpl implements WorkerMsgHelper {

    @Autowired
    NetworkClient networkClient;

    @Override
    public void send(String topic, String message) throws Exception {
        this.networkClient.send(topic, message);
    }
    @Override
    public void send(String topic, Realtime message) throws Exception {
        networkClient.send(topic, message);
    }
    @Override
    public void send(String topic, Request message) throws Exception {
        networkClient.send(topic, message);
    }
    @Override
    public void send(String topic, Response message) throws Exception {
        networkClient.send(topic, message);
    }
    @Override
    public void broadcastWorker(String message) throws Exception {
        networkClient.broadcastWorker(message);
    }
    @Override
    public void broadcastWorker(Realtime message) throws Exception {
        networkClient.broadcastWorker(message);
    }
    @Override
    public void broadcastWorker(Request message) throws Exception {
        networkClient.broadcastWorker(message);
    }
    @Override
    public void broadcastWorker(Response message) throws Exception {
        networkClient.broadcastWorker(message);
    }
}
