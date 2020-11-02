package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

public class Server implements Runnable {

    int port;
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
                // A serverSocket.accept() methods waits for any connection to be made from the client.
                Socket socket = serverSocket.accept();
                initSocket(socket);

                // IP address
                InetAddress inetAddress = socket.getInetAddress();

                System.out.println("Connected to: " + inetAddress.getHostName());
                System.out.println("With IP address: " + inetAddress.getHostAddress() + " at " + new Date() + '\n');

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // When a connection is established a thread will be started up for that connection
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
