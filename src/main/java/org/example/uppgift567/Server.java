package org.example.uppgift567;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class Server {

    private final int port = 55555;

    public Server() {
        // DETTA SKALL SKICKA SERIALISED USER POJO:s Eller Strings (Error msg)
        // Vilket ObjectOutputStream sköter
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            //PrintWriter ut = new PrintWriter(socket.getOutputStream(), true);
            ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {

            // Skickar ett objekt som säger att vi är connected med Client
            ut.writeObject(new Intro(true));

            Object clientMessage;
            while ((clientMessage = in.readObject()) != null) {
                // Client message, kan hantera String + Object
                System.out.println(timestamp() + " - " + clientMessage);

                // Response to Client
                // Serialised Objekt skickas istället
                try {
                    User foundUser = new FakeDatabase().findByFullName((String) clientMessage);
                    // Wrap User i en Response Object
                    ut.writeObject(new Response(true, foundUser, ""));
                    ut.flush();
                } catch (NoSuchElementException ex) {
                    ut.writeObject(new Response(false, null, ex.getMessage()));
                    ut.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Best practice i en metod annars kanske inte tiden uppdateras!
    // Har kod duplication på denna i Server så till nästa gång kanske göra en Service Klass
    private String timestamp() {
        // Timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

/*    private String getUserByName(String name) {
        try {
            User found = new FakeDatabase().findByFullName(name);
            return found.getFullName() + " " + found.getDateOfBirth() + " " + found.getMobile()
                    + " " + found.getAddress();
        } catch (NoSuchElementException ex) {
            return ex.getMessage();
        }
    }*/

    public static void main(String[] args) {
        new Server();
    }
}
