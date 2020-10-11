package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;

public class Server {

    // Variables
    IPAddress ipAddress = new IPAddress();
    private int port;
    private Thread readThread;
    private boolean reading = false;
    private DatagramSocket socket;                         // Establish a socket which the computer will use to send data over the network

    private final int MAX_PACKET_SIZE = 1024;              // 1 kilobyte

    // We allocate this once for each server object and then we can continually reuse that buffer
    private byte[] receivedDataBuffer = new byte [MAX_PACKET_SIZE * 10];

    public void startServer(int port) {

        this.port = port;

        try {                                               // Define a block of code to be tested for errors while it is being executed
            socket = new DatagramSocket(port);              // Socket created, this will be used for all incoming and outgoing connections
        } catch (SocketException e) {                       // Define a block of code to be executed, if an error occurs in the try block
            e.printStackTrace();
            return;
        }

        readThread = new Thread(() -> read(), "Server - readThread");
        readThread.start();

    }

    private void read(){                                    // A separate thread that read things       (It's our "mailbox")
        while(reading) {                                    // The while loop loops through a block of code as long as a "reading" is true
            /* Having multiple threads makes the program able to do more things at the same time while having a while loop that blocks
            eg. process packets and send data whilst also reading */

            /* When we have a packet that we don't want to send anywhere we just need the
               constructor that has the buffer and the length instead of InetAddress, port etc.*/
            DatagramPacket packet = new DatagramPacket(receivedDataBuffer, MAX_PACKET_SIZE);

            /* Receive method will receive a datagram packet from the socket when this method returns,
            the datagram packet buffer is filled with the data received */
            try {
                socket.receive(packet);                     // This method blocks until a datagram packet is received
            } catch (IOException e) {
                e.printStackTrace();
            }
            write(packet);                                  // Here we process the packet that we just received
        }
    }

    private void write(DatagramPacket packet){              // Process a packet (UDP packet = DatagramPacket in Java)
         byte[] data = packet.getData();
        if(new String(data, 0, 4).equals("RCDB")){
           // RCDatabase database = RCDatabase.Deserialize(data);
            //String username = database.findObject("root").findString("username").getString();
            //write(database);
        } else{
            switch (data[0]) {
                case 1:
                    //connection packet
                    break;
                case 2:
                    //Ping packet
                    break;
                case 3:
                    //Login attempt packet
                    break;
            }

        }

    }

   // private void read(RCDatabase database){

   // }

    //Send an UDP packet over the network
    public void send(byte[] data, InetAddress address, int port) {                       // Send some data to an address
        assert(socket.isConnected());
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);                //Creating our packet (It's our "letter")
        try {
            socket.send(packet);                                                                     // Putting our "letter" in the "mailbox"
        } catch (IOException e) {
            e.printStackTrace();
        }




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

            // Create data input and output streams
            DataInputStream fromClient = new DataInputStream(
                    connectToClient.getInputStream());
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
