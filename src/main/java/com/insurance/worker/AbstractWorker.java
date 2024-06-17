package com.insurance.worker;

import com.insurance.worker.network.MessageHandler;
import com.insurance.worker.network.Topics;
import com.insurance.worker.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractWorker extends WorkerMsgHelperImpl implements BaseWorker {
    private static Logger logger = LoggingUtil.createLogger(AbstractWorker.class);
    private String workerName;

    @Autowired
    MessageHandler handler;

    @Override
    public void start(String workerName) throws Exception {
        logger.info("START WORKER: " + workerName);
        this.workerName = workerName;

        //--kết nối vào broker: sử dung network client
        networkClient.start(handler);

        //--đăng ký queue và các topic mặc định cho worker: pub/sub
        subcribeToppic(Topics.WORKER_BROADCAST);
        subcribeToppic(String.format(Topics.WORKER_NAME, workerName));

        //cho phép người dùng đăng ký thêm topic
        onStarted();
    }

    @Override
    public abstract void onStarted();

    @Override
    public abstract void onStoping();

    @Override
    public void stop() throws Exception{
        logger.info("STOP WORKER: " + this.workerName);

        //gọi huỷ đăng ký topic của người dùng
        onStoping();

        //huỷ đăng ký topic mặc định
        unSubcribeToppic(Topics.WORKER_BROADCAST);
        unSubcribeToppic(String.format(Topics.WORKER_NAME, workerName));

        //ngắt kết nối
        networkClient.stop();
    }

    @Override
    public void subcribeToppic(String topic) throws Exception {
        networkClient.subcribeToppic(topic);
    }

    @Override
    public void unSubcribeToppic(String topic) throws Exception {
        networkClient.unSubcribeToppic(topic);
    }
}
