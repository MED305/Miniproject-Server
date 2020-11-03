package com.company;

public class Main {

    public static void main(String[] args) {

	// write your code here
        // hej Tara det her er super fedt!!

        // Variables
        int port = 6969;

        Server server = new Server();
        server.server(port);
        server.start();
    }
}