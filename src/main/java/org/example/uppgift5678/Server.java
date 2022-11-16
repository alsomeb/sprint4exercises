package org.example.uppgift5678;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    Protocol protocol = new Protocol();
    private final int port = 55555;

    public Server() {
        // DETTA SKALL SKICKA SERIALISED USER POJO:s Eller Strings (Error msg)
        // Vilket ObjectOutputStream sköter
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {

            // Skickar ett objekt som säger att vi är connected med Client
            // behöver ej skicka in något och vi kommer ej kolla av något heller!
            ut.writeObject(protocol.getOutput(null));

            Object clientMessage;
            while ((clientMessage = in.readObject()) != null) {
                // Client message, kan hantera String + Object
                System.out.println(timestamp() + " - " + clientMessage);

                // Response to Client
                // Serialised Objekt skickas mha ObjectOutputStream i metod nedan
                // Protocol hanterar business logic och de som skall skickas till client
                ut.writeObject(protocol.getOutput(clientMessage));
                ut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Bara response objektet som får timestamp, så vi måste ha en här också för logging i servern
    private String timestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) {
        new Server();
    }
}
