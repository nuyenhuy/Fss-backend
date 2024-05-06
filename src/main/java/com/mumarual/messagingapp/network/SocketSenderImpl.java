package com.mumarual.messagingapp.network;
import com.mumarual.messagingapp.message.SocketData;
import com.mumarual.messagingapp.util.CompareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class SocketSenderImpl implements SocketSender {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void sendTo(SocketData data) throws Exception {
        if(CompareUtil.isEqualsNullOrEmpty(data.getRecTopic()))
            throw new Exception("No topic to send");
        messagingTemplate.convertAndSend(data.getRecTopic(), data);
    }

    @Override
    public void sendToTopic(String topic, SocketData data) {
        messagingTemplate.convertAndSend(topic, data);
    }
}
