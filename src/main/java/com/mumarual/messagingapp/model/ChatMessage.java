package com.mumarual.messagingapp.model;

public class ChatMessage extends SocketData {
    private ActionType type;
    private String content;

    public ChatMessage(){}
    public ChatMessage(String recTopic) {
        super(recTopic);
    }
    public ChatMessage(String recTopic, ActionType type) {
        super(recTopic);
        this.type = type;
    }

    public enum ActionType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
