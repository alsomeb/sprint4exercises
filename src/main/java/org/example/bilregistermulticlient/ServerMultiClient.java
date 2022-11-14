package org.example.bilregistermulticlient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiClient {
    private final int port = 55555;
    private ServerSocket serverSocket;

    public ServerMultiClient() throws IOException {
        serverSocket = new ServerSocket(port);
        startServer();

    }

    private void startServer() {

        //while loopen så länge en socket inte är stängd
        //serverSocket.accept() -> lyssnar efter connections i loopen

        // Så länge server socket inte är stängd, lyssna efter client connections
        while (!serverSocket.isClosed()) {
            try (Socket socket = serverSocket.accept()) {
                new Thread(new ClientHandler(socket)).start();

                // För att få client IP som connect till server
                InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String clientIpAddress = socketAddress.getAddress().getHostAddress();
                System.out.println(clientIpAddress + " has connected!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            new ServerMultiClient();
        } catch (IOException e) {
            System.out.println("An I/O error occurred when opening the socket");
            e.printStackTrace();
        }
    }
}


