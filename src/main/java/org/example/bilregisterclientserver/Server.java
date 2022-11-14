package org.example.bilregisterclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Server {
    // server, måste startas först

    private final Database carDatabase = new Database();
    private final int port = 55555;
    private final String welcomeMsg = "Welcome to our Car Database, please enter a Reg Nr to search:";

    public Server() {
        try(ServerSocket serverSocket = new ServerSocket(port);
            // Ligger o väntar på client
            Socket socket = serverSocket.accept();
            // Både inström och utström WRAPPAD OCH KLAR,
            // ALLTID UTSTRÖM FÖRST! BEST PRACTICE
            // Ut ström måste ha autoFlush true!
            PrintWriter ut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            ut.println(welcomeMsg);

            // Meddelande från client
            String clientRequest;

            // READ (BufferedReader)
            while ((clientRequest = in.readLine()) != null) {
                // the getRemoteSocketAddress method returns an object of type SocketAddress.
                // This is an abstract Java class. In this instance, we know it's a TCP/IP connection, so we can cast it to InetSocketAddress:
                InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

                // As we've already seen, a socket address is a combination of an IP address and port number.
                // We can use getAddress to get the IP address. This returns an InetAddress object.
                // However, we can also use getHostAddress to get a string representation of the IP address:
                String clientIpAddress = socketAddress.getAddress()
                        .getHostAddress();

                // Now we can display client IP + request
                System.out.println(clientIpAddress + " - Request: " + clientRequest);

                // WRITE (PrintWriter)
                searchByRegNr(clientRequest, ut);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchByRegNr(String request, PrintWriter ut) {
        try {
            Car foundCar = carDatabase.searchByRegNr(request.trim());
            ut.println(foundCar.getRegNr() + " " + foundCar.getOwner() + " " + foundCar.getBrand() + " " + foundCar.getColor());
        } catch (NoSuchElementException e) {
            ut.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
