package org.example.uppgift5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class Server {

    private final int port = 55555;
    private final String welcomeMsg = "Ange namn som du vill veta telefon nummer till:";

    public Server() {
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            PrintWriter ut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            ut.println(welcomeMsg);

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                // Client message
                System.out.println(timestamp() + " - " + clientMessage);

                // Response to Client
                ut.println(getUserByName(clientMessage));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getUserByName(String name) {
        try {
            User found = new FakeDatabase().findByFullName(name);
            return found.getFullName() + " " + found.getDateOfBirth() + " " + found.getMobile()
                    + " " + found.getAddress();
        } catch (NoSuchElementException ex) {
            return ex.getMessage();
        }
    }

    // Best practice i en metod annars kanske inte tiden uppdateras!
    private String timestamp() {
        // Timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) {
        new Server();
    }
}
