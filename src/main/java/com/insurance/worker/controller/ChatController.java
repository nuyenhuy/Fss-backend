package com.insurance.worker.controller;
import com.insurance.worker.network.Topics;
import com.mumarual.messagingapp.broker.Sender;
import com.insurance.worker.model.ChatMessage;
import com.insurance.worker.util.CompareUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
@Controller
public class ChatController {
    private Sender sender;
    @RequestMapping(value="/chat", method = RequestMethod.GET)
    public String chatPage(ModelMap model){
        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo(Topics.CHAT_PUBLIC)
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        sender.send(Topics.CHAT_PUBLIC, chatMessage);
        System.out.println(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo(Topics.CHAT_PUBLIC)
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        Map<String, Object> attrs = headerAccessor.getSessionAttributes();
        if(CompareUtil.isEqualsNull(attrs)) {
            attrs = new HashMap<>();
            headerAccessor.setSessionAttributes(attrs);
        }
        attrs.put("username", chatMessage.getSender());
        return chatMessage;
    }
}
