package org.example.exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client {

    // Servern måste alltid först startas ändå för den måste upprätta connection
    // Den ligger och väntar på client startas och det blir ett 3-way-handshake
    public Client() {
        int port = 55555;
        String host = "127.0.0.1";

    // Här skall Client Läsa in meddelande från Servern
        try(Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            String messageFromServer;
            while ((messageFromServer = in.readLine()) != null) {
                System.out.println(messageFromServer);
            }

        } catch (IOException e) {
            System.err.println("Starta server först");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
