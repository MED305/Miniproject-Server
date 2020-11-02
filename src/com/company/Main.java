package com.company;

public class Main {

    public static void main(String[] args) {
        // Variables
        int port = 7070;

        Server server = new Server();
        server.server(port);
        server.start();
    }
}