package org.example.uppgift567810.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerHandler {

    private Protocol protocol = new Protocol();
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;

        // Returns the address of the endpoint this socket is connected
        InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
        handler(socketAddress);
    }

    private void handler(InetSocketAddress socketAddress) {
        // Returns the IP address string in textual presentation.
        String clientIpAddress = socketAddress.getAddress().getHostAddress();

        // DETTA SKALL SKICKA SERIALISED USER POJO:s Eller Strings (Error msg)
        // Vilket ObjectOutputStream sköter
        try(ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // Skickar ett objekt som säger att vi är connected med Client
            // behöver ej skicka in något och vi kommer ej kolla av något heller!
            // Bara för att trigga igång protocol med STATE:s
            ut.writeObject(protocol.getOutput(null));

            Object clientMessage;
            while ((clientMessage = in.readObject()) != null) {
                // Client message, kan hantera String + Object
                System.out.println(clientIpAddress + " " + timestamp() + " - " + clientMessage);

                // Response to Client
                // Serialised Objekt skickas mha ObjectOutputStream
                // Protocol hanterar business logic och sedan fokuserar servern bara på skicka / ta emot
                ut.writeObject(protocol.getOutput(clientMessage));
                ut.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Bara response objektet som får timestamp, så vi måste ha en här också för logging i servern
    private String timestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

}
