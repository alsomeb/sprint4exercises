package org.example.bilregisterclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final int port = 55555;
    private final String hostName = "127.0.0.1";

    public Client() {
        try(Socket socket = new Socket(hostName, port);
            // Både inström och utström WRAPPAD OCH KLAR, Både i Client & Server
            // ALLTID UTSTRÖM FÖRST! BEST PRACTICE
            PrintWriter ut = new PrintWriter(socket.getOutputStream(), true);

            // För att ta emot från Server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Kunna ta emot vad client skrev på tangentbordet = System.in
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // Skapar variabler som kommer få värden från server (response) / client (System.in)
            String fromServer;
            String fromUser;

            // Läser från servern (Välkomst Meddelandet) Kan ej va i loopen nedan kommer ej upp något då
            fromServer = in.readLine();
            System.out.println(fromServer);

            // Användaren skriver in något, vi skickar det till server
            while ((fromUser = userInput.readLine()) != null) {
                ut.println(fromUser);
                System.out.println("Search Query: " + fromUser);

                // Response från server
                fromServer = in.readLine();
                System.out.println(fromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
