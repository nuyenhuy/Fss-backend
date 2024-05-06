package com.mumarual.messagingapp.network;

import com.mumarual.messagingapp.message.SocketData;

public class LoginMessage extends SocketData {
    private String userid;
    private String password;

    public LoginMessage(){}
    public LoginMessage(String recTopic) {
        super(recTopic);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
