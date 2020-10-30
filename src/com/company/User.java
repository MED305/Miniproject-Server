package com.company;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {

    private String userName;
    public String chatMessage;
    public InetAddress inetAddress;
    private int id;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    void setUserName(String userName) {
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

    @Override
    public String toString() {
        return "ID: " + this.id + " Name: " + this.userName;
    }
}
