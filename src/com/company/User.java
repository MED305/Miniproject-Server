package com.company;

import java.net.InetAddress;

public class User {

    private double userName;
    public String chatMessage;
    public InetAddress inetAddress;

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

    InetAddress getInetAddress() {
        return this.inetAddress;
    }

    void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }
}
