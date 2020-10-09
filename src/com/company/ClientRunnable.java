package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRunnable implements Runnable{
    private Socket socket;

    ClientRunnable(Socket a_socket) {
        this.socket = a_socket;
    }

    public void run() {

        try {

            // An inputStream lets an application read primitive java data types.
            DataInputStream isFromClient = new DataInputStream(
                    socket.getInputStream());

            // This writes primitive data types to a stream that can be ported.
            DataOutputStream toClient = new DataOutputStream(
                    socket.getOutputStream());

            while(true) {

                // Test receiver
                double number = isFromClient.readDouble();

                // Test calculator
                double sendBackNumber = number * 100;

                // Test send back
                toClient.writeDouble(sendBackNumber);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
