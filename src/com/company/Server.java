package com.company;

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

            // A system message pritning out the connected IP address and the date at which it happen.
            System.out.println("Connected to IP: " + ipAddress.getAddress() +  "at " + new Date() + '\n');


            // Catch = define a block of code to be executed if an error occurs in "try"
        } catch(
                IOException e) {
            System.err.println(e);
        }
    }
}
