package com.mumarual.messagingapp.config;

import com.mumarual.messagingapp.model.ChatMessage;
import com.mumarual.messagingapp.network.SocketSender;
import com.mumarual.messagingapp.network.Topics;
import com.mumarual.messagingapp.util.CompareUtil;
import com.mumarual.messagingapp.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

@Component
public class WebSocketEventListener {
    private static Logger logger = LoggingUtil.createLogger(WebSocketEventListener.class);

    @Autowired
    private SocketSender socketSender;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("New web socket connection!");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("New socket connection disconnected!");

        //xu ly notify for chat room
        Map<String, Object> attrs = headerAccessor.getSessionAttributes();
        if(!CompareUtil.isEqualsNullOrEmpty(attrs) && attrs.containsKey("username")) {
            String username = (String) attrs.get("username");
            if (username != null) {
                try {
                    String recTopic = Topics.CHAT_PUBLIC;
                    ChatMessage chatMessage = new ChatMessage(recTopic, ChatMessage.ActionType.LEAVE);
                    chatMessage.setSender(username);

                    socketSender.sendTo(chatMessage);
                }
                catch (Exception ex){
                    logger.error(ex.getMessage());
                }
            }
        }
    }
}
