package org.example.exercise1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() {
        // Här skall servern kunna Skicka så vi måste nog ha en Writer påkopplad ist för Reader
        int port = 55555;

        // Try with resources best practice
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket sock = serverSocket.accept();
            // Viktigt att flush här!
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true))
        {

            while (true) {
                String message = "Hej från server!";
                out.println(message);
                System.out.println(message + " sent to Client");
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("an I/O error occurred when opening the socket or Thread related");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
