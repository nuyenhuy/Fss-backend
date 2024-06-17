package com.insurance.worker;

public interface BaseWorker {

    void start(String workerName) throws Exception;
    void stop() throws Exception;
    void onStarted() throws Exception;
    void onStoping() throws Exception;

    void subcribeToppic(String topic) throws Exception;
    void unSubcribeToppic(String topic) throws Exception;
}
