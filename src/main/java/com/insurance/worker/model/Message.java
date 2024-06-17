package com.insurance.worker.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message {
    private MessageType type;
    private String content;
    private String sender;
    private String sessionId;
}

