package com.insurance.worker.controller;
import com.insurance.worker.util.CompareUtil;
import com.insurance.worker.util.LoggingUtil;
import com.insurance.worker.network.SocketSender;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private static Logger logger = LoggingUtil.createLogger(UserController.class);
    @Autowired
    SocketSender sender;

    @MessageMapping("/user.login")
    public void userLogin(@Payload LoginMessage loginMessage,
                          SimpMessageHeaderAccessor headerAccessor) {
        try {
            loginMessage.setRecTopic(loginMessage.getSenTopic());
            loginMessage.setRecever(loginMessage.getSender());
            //todo: call service
            //test check dummy data
            if (CompareUtil.isEquals(loginMessage.getUserid(), "admin")
                    && CompareUtil.isEquals(loginMessage.getPassword(), "admin")) {
                loginMessage.setCode(0L);
                // Add username in web socket session
                Map<String, Object> attrs = headerAccessor.getSessionAttributes();
                if (CompareUtil.isEqualsNull(attrs)) {
                    attrs = new HashMap<>();
                    headerAccessor.setSessionAttributes(attrs);
                }
                attrs.put("username", loginMessage.getUserid());
            } else
                loginMessage.setCode(2L);
            sender.sendTo(loginMessage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @MessageMapping("/user.logout")
    public void userLogout(@Payload LoginMessage loginMessage,
                           SimpMessageHeaderAccessor headerAccessor) {
        // remove username in web socket session
        Map<String, Object> attrs = headerAccessor.getSessionAttributes();
        if (CompareUtil.isEqualsNull(attrs)) {
            attrs = new HashMap<>();
            headerAccessor.setSessionAttributes(attrs);
        }
        attrs.remove("username", loginMessage.getUserid());
    }
}
