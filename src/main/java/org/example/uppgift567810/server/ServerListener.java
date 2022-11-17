package org.example.uppgift567810.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private final int port = 55555;
    ServerSocket serverSocket;

    public ServerListener() throws IOException {
        serverSocket = new ServerSocket(port);
        startServer();
    }

    public void startServer() throws IOException {
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            // Startar en ny server handler för varje client, separat instans
            new Thread(() -> new ServerHandler(socket)).start();
        }
    }

    public static void main(String[] args) {
        try {
            new ServerListener();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
