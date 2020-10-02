package com.company;

// Imports
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class GameServer {

    public static void main(String[] args) {
        // Variables
        int port = 6969;    // Server port

        // Write code
        try {       // Try = define a block of code to be tested.

            // Instead of Socket = client side, a ServerSocket = server side.
            ServerSocket serverSocket = new ServerSocket(port);

            // A system message to indicate at which time the server is executed.
            System.out.println("Game Server has been started at " + new Date() + '\n');

            // A serverSocket.accept() methods waits for any connection to be made from the client. This throws an IOException, which is why the code is in a try & catch statement.
            Socket connectToClient = serverSocket.accept();

            

        } catch(IOException e) {        // Catch = define a block of code to be executed if an error occurs in "try"
            System.err.println(e);
        }
    }
}
