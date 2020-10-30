package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable {

    int port;
    public int connectionCounter = 0;
    boolean running = false;
    ServerSocket serverSocket;

    public void server(int port) {
        this.port = port;

        try {

            // Instead of Socket = client side, a ServerSocket = server side.
            serverSocket = new ServerSocket(port);

            // A system message to indicate at which time the server is executed.
            System.out.println("Game Server has been started at " + new Date() + '\n');

        } catch (
                IOException e) {
            System.err.println(e);
        }
    }

    public void start () {
        new Thread(this).start();
    }

    @Override
    public void run () {
        running = true;

        while (running) {
            try {
                System.out.println("Amount of connections since live: " + connectionCounter);

                // A serverSocket.accept() methods waits for any connection to be made from the client.
                Socket socket = serverSocket.accept();
                initSocket(socket);
                connectionCounter ++;

                // IP address
                InetAddress inetAddress = socket.getInetAddress();

                System.out.println("Connected to Host: " + inetAddress.getHostName());
                System.out.println("With IP address: " + inetAddress.getHostAddress() + " at " + new Date() + '\n');


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeServer();
    }

    private void initSocket(Socket socket) {
        Connection connection = new Connection(socket);
        new Thread(connection).start();
    }

    public void closeServer() {
        running = false;

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
