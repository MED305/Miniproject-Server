package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Runnable {

    private Socket socket;
    private String userName;
    public String inetAddress;
    private ObjectInputStream isFromClient;
    private ObjectOutputStream toClient;
    public float userX;
    public float userY;

    public Client(Socket socket) {
        this.socket = socket;


        try {
            // Object output/input is used to read/write any object types, less efficient but covers more
            // Data output/input is simpler and can only read/write primitive types.
            toClient = new ObjectOutputStream(socket.getOutputStream());
            isFromClient = new ObjectInputStream(socket.getInputStream());

            String IP = socket.getInetAddress().getHostAddress();
            setInetAddress(IP);

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }


    public void run() {

        try {

            while (socket.isConnected()) {

                setUserXY(isFromClient.readFloat(), isFromClient.readFloat());

                sendObject();

            }

        } catch (SocketException e) {
            System.out.println("Client with IP: " + socket.getInetAddress() + " has disconnected.");
            close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject() {
        for (Client client : Server.clients) {
            if (client != this) {
                try {
                    client.toClient.writeFloat(this.getUserX());
                    client.toClient.writeFloat(this.getUserY());
                    toClient.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    String getUserName() {
        return this.userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    void setUserXY(float x, float y) {
        userX = x;
        userY = y;
    }

    public float getUserX() {
        return userX;
    }

    public float getUserY() {
        return userY;
    }

    String getInetAddress() {
        return inetAddress;
    }

    void setInetAddress(String inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void close() {
        try {
            isFromClient.close();
            toClient.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
