package org.example.clientservertcpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Sitter man på samma nätverk kan man skicka till varandras datorer
    // Client måste alltid koppla upp sig mot Server först
    public Server() {
        // ALLT DETTA GÖRS I "TRY WITH RESOURCES", best practice!
        try(ServerSocket serverSocket = new ServerSocket(55555);
            Socket sock = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream())))
        {
            System.out.println("Incoming traffic from client");
            // Ligga här och ta emot messages från Client
            String message;
            while ((message = bufferedReader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An I/O error occurred when opening the socket.");
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
