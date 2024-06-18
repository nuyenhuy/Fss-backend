package com.insurance.worker.network;

import com.insurance.worker.WorkerMsgHelperImpl;
import com.insurance.worker.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MessageHandlerImpl extends WorkerMsgHelperImpl implements MessageHandler {
    private static Logger logger = LoggingUtil.createLogger(MessageHandlerImpl.class);

    @Override
    public void onNewMessage(String msg) {
        logger.info("Handler:String: " + msg);
    }

    @Override
    public void onNewMessage(Realtime msg) {

    }

    @Override
    public void onNewMessage(Request msg) {

    }

    @Override
    public void onNewMessage(Response msg) {

    }
}
