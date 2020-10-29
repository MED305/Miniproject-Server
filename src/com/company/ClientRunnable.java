package com.company;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientRunnable implements Runnable{
    private Socket socket;

    User user = new User();
    boolean connected = true;

    ClientRunnable(Socket a_socket) {
        this.socket = a_socket;
    }

    public void run() {

        try {

            // An inputStream lets an application read primitive java data types.
            DataInputStream isFromClient = new DataInputStream(socket.getInputStream());

            // This writes primitive data types to a stream that can be ported.
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());


            while(connected) {

                double userNumber = isFromClient.readDouble();
                toClient.writeDouble(userNumber);
                this.user.setUserName(userNumber);

                // Test: confirming userNumber
                System.out.println(this.user.getUserName());

                byte messageType = isFromClient.readByte();
                System.out.println("Message: " + messageType);

                /*
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
