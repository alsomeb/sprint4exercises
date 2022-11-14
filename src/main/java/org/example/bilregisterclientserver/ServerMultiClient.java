package org.example.bilregisterclientserver;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class ServerMultiClient {
    private final Database carDatabase = new Database();
    private final int port = 55555;
    private final String welcomeMsg = "Welcome to our Car Database, please enter a Reg Nr to search:";
    private ServerSocket serverSocket;
    private final ArrayList<Socket> clientSockets = new ArrayList<>();

    public ServerMultiClient() throws IOException {
        serverSocket = new ServerSocket(port);

    }

    private void startServer() {
        //try-catch
        //while loopen så länge en socket inte är stängd
        //serverSocket.accept() -> lyssnar efter connections i loopen

        // Så länge server socket inte är stängd, lyssna efter client connections
        while (!serverSocket.isClosed()) {
            try (Socket socket = serverSocket.accept()) {

                // Lagrar client connection (socket)
                clientSockets.add(socket);

                // För att få client IP som connect till server
                InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String clientIpAddress = socketAddress.getAddress().getHostAddress();
                System.out.println(clientIpAddress + " has connected!");



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    }
}


