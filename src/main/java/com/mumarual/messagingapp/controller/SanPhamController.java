package com.mumarual.messagingapp.controller;

import com.mumarual.messagingapp.broker.Sender;
import com.mumarual.messagingapp.model.Product;
import com.mumarual.messagingapp.network.Topics;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
@RestController
@RequestMapping("api/v1/product")
public class SanPhamController {
    private final SimpMessageSendingOperations messagingTemplate;

    public SanPhamController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @PutMapping(path={"{productId}"})
    @SendTo(Topics.UPDATE_PRODUCT_TOPIC)
    public Product addNewProduct(@PathVariable(value = "productId") Integer id,
                                 @RequestBody Product product) {
        messagingTemplate.convertAndSend("/socket/topic/update/product", product);
        return product;
    }

}
