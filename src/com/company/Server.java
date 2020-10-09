package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    // Variables
    IPAddress ipAddress = new IPAddress();

    public void startServer(int port) {
        // Try = define a block of code to be tested.
        try {

            // Instead of Socket = client side, a ServerSocket = server side.
            ServerSocket serverSocket = new ServerSocket(port);

            // A system message to indicate at which time the server is executed.
            System.out.println("Game Server has been started at " + new Date() + '\n');

            // A serverSocket.accept() methods waits for any connection to be made from the client. This throws an IOException, which is why the code is in a try & catch statement.
            Socket connectToClient = serverSocket.accept();

            // A system message printing out the connected IP address and the date at which it happen.
            System.out.println("Connected to IP: " + ipAddress.getAddress() +  "at " + new Date() + '\n');

            // An inputStream lets an application read primitive java data types.
            DataInputStream fromClient = new DataInputStream(
                    connectToClient.getInputStream());

            // This writes primitive data types to a stream that can be ported.
            DataOutputStream toClient = new DataOutputStream(
                    connectToClient.getOutputStream());

            // A while loop which keeps going as long as a specified condition is true
            while (true) {
                // Test receiver
                double number = fromClient.readDouble();

                // Test calculator
                double sendBackNumber = number * 100;

                // Test send back
                toClient.writeDouble(sendBackNumber);
            }

            // Catch = define a block of code to be executed if an error occurs in "try"
        } catch(
                IOException e) {
            System.err.println(e);
        }
    }
}
