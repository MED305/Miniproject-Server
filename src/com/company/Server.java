package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    int connectionCounter = 0;
    boolean clientConnected = true;

    public void startServer(int port) {

        new Thread(() -> {

            // Try = define a block of code to be tested.
            try {

                // Instead of Socket = client side, a ServerSocket = server side.
                ServerSocket serverSocket = new ServerSocket(port);

                // A system message to indicate at which time the server is executed.
                System.out.println("Game Server has been started at " + new Date() + '\n');

                while (clientConnected) {

                    System.out.println("Amount of connections since live: " + connectionCounter);

                    // A serverSocket.accept() methods waits for any connection to be made from the client. This throws an IOException, which is why the code is in a try & catch statement.
                    Socket connectToClient = serverSocket.accept();
                    connectionCounter ++;

                    // IP address
                    InetAddress inetAddress = connectToClient.getInetAddress();

                    System.out.println("Connected to Host: " + inetAddress.getHostName());
                    System.out.println("With IP address: " + inetAddress.getHostAddress() + " at " + new Date() + '\n');

                    // Initiating new thread when a client is connected
                    new Thread(new ClientRunnable(connectToClient)).start();
                }
                // Catch = define a block of code to be executed if an error occurs in "try"
            } catch (
                    IOException e) {
                System.err.println(e);
            }
        }).start();
    }
}
