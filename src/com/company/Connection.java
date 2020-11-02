package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;



public class Connection implements Runnable{
    private Socket socket;
    private ObjectInputStream isFromClient;
    private ObjectOutputStream toClient;
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

                float test = isFromClient.readFloat();
                System.out.println(test);

                // Test: confirming things work
                //System.out.println("Username: " + this.user.getUserName() + "\nWith IP-Address: " + this.user.getInetAddress().getHostAddress());

                /*
                byte messageType = isFromClient.readByte();
                System.out.println("Message: " + messageType);

                // receiver for doubles
                double number = isFromClient.readDouble();

                // receiver for char
                char clientMessage = isFromClient.readChar();

                // processing the received double
                double sendBackNumber = number * 100;

                // returning a new value after being processed in this case a double
                toClient.writeDouble(sendBackNumber);
                 */
            }

        } catch (SocketException e) {
            System.out.println("Client with IP: " + user.getInetAddress() + " has disconnected.");
            close();
        }
        catch (IOException e) {
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

    public void sendObject (Object packet) {
        try {
            toClient.writeObject(packet);
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
