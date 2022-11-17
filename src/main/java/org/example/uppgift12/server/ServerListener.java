package org.example.uppgift12.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private final int port = 55555;
    private final ServerSocket serverSocket;

    public ServerListener() throws IOException {
        serverSocket = new ServerSocket(port);
        startServer();
    }

    public void startServer() throws IOException{
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            // Startar en ny server handler för varje client, separat instans
            // Sparar alla connectade clientSockets i en lista!
            new Thread(() -> {
                try {
                    new ServerHandler(socket);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }).start();
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
