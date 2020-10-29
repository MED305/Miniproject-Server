package com.company;

import java.net.InetAddress;
import java.net.Socket;

public class User {

    private double userName;
    public String chatMessage;

    double getUserName() {
        return this.userName;
    }

    void setUserName(double userName) {
        this.userName = userName;
    }

    String getMessage() {
        return this.chatMessage;
    }

    void setMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
