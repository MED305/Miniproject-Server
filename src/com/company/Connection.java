package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;


/*
public class Connection implements Runnable{
    private Socket socket;
    private ObjectInputStream isFromClient;
    private ObjectOutputStream toClient;

    public Connection(Socket socket) {
        this.socket = socket;

        try {
            // Object output/input is used to read/write any object types, less efficient but covers more
            // Data output/input is simpler and can only read/write primitive types.
            toClient = new ObjectOutputStream(socket.getOutputStream());
            isFromClient = new ObjectInputStream(socket.getInputStream());

            String IP = socket.getInetAddress().getHostAddress();
            client.setInetAddress(IP);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {

            while(socket.isConnected()) {

                this.client.setUserXY(isFromClient.readFloat(), isFromClient.readFloat());

                sendObject();

            }

        } catch (SocketException e) {
            System.out.println("Client with IP: " + client.getInetAddress() + " has disconnected.");
            close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close () {
        try {
            isFromClient.close();
            toClient.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject() {
        try {
            toClient.writeFloat(client.getUserX());
            toClient.writeFloat(client.getUserY());
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/