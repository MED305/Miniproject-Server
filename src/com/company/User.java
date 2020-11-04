package com.company;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    public String inetAddress;
    private int id;
    public float userX;
    public float userY;

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

    private void setUserXY(float x, float y) {
        this.userX = x;
        this.userY = y;
    }

    public float getUserX() {
        return this.userX;
    }

    public float getUserY() {
        return this.userY;
    }

    String getInetAddress() {
        return this.inetAddress;
    }

    void setInetAddress(String inetAddress) {
        this.inetAddress = inetAddress;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " Name: " + this.userName;
    }
}
