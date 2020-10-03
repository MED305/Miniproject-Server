package com.company;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        // Variables
        int port = 6969;                                    // Port for the server

        Server server = new Server();
        server.startServer(port);

        InetAddress address = null;
        try{
            address = InetAddress.getByName("IP ADDRESS");      // This is where it should send the data to
            /* This throws an exception if it does'nt work (if the IP address cant be encrypted it will tell us that it does'nt
            know what the host is and it will return that) */
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        server.send(new byte [] { 0, 1, 2 }, address, port);                // Bytes of Data (in this case 3 bytes of data 0,1,2)

    }
}