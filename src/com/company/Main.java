package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Variables
        int port = 6969;

        Server server = new Server();
        server.server(port);
        server.start();
    }
}