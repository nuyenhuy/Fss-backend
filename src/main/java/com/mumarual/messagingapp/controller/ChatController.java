package com.mumarual.messagingapp.controller;
import com.mumarual.messagingapp.broker.Sender;
import com.mumarual.messagingapp.message.ChatMessage;
import com.mumarual.messagingapp.network.Topics;
import com.mumarual.messagingapp.util.CompareUtil;
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
        sender.send(Topics.CHAT_PUBLIC, chatMessage);
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
