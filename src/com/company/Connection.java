package com.company;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;



public class Connection implements Runnable{
    private Socket socket;
    private ObjectInputStream isFromClient;
    private ObjectOutputStream toClient;
    private ObjectInputStream ois = null;
    private int id;

    User user = new User(id, "");

    public Connection(Socket socket) {
        this.socket = socket;
        id = 0;

        try {
            // Object output/input is used to read/write any object types, less efficient but covers more
            // Data output/input is simpler and can only read/write primitive types.
            toClient = new ObjectOutputStream(socket.getOutputStream());
            isFromClient = new ObjectInputStream(socket.getInputStream());

            String IP = socket.getInetAddress().getHostAddress();
            user.setInetAddress(IP);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            while(socket.isConnected()) {

                user.userX = isFromClient.readFloat();
                user.userY = isFromClient.readFloat();

                sendObject();

            }

        } catch (SocketException e) {
            System.out.println("Client with IP: " + user.getInetAddress() + " has disconnected.");
            close();

        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
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
            toClient.writeFloat(user.getUserX());
            toClient.writeFloat(user.getUserY());
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}