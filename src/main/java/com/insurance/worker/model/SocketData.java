package com.insurance.worker.model;

import java.io.Serializable;

public class SocketData implements Serializable {
    private String msgType;
    private String senTopic;
    private String recTopic;
    private String sender;
    private String recever;
    private Long code;

    public SocketData(){}
    public SocketData(String recTopic){
        this.recTopic = recTopic;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSenTopic() {
        return senTopic;
    }

    public void setSenTopic(String senTopic) {
        this.senTopic = senTopic;
    }

    public String getRecTopic() {
        return recTopic;
    }

    public void setRecTopic(String recTopic) {
        this.recTopic = recTopic;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecever() {
        return recever;
    }

    public void setRecever(String recever) {
        this.recever = recever;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
