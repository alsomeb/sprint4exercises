package org.example.uppgift5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Client {

    private final int port = 55555;

    // Returns the raw IP address string in textual presentation
    private final String ip = InetAddress.getLocalHost().getHostAddress();

    public Client() throws UnknownHostException {
        System.out.println(ip);
        // String hostName + port för socket
        try(Socket clientSocket = new Socket(ip, port);
            PrintWriter ut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)))
        {
            // Skapar upp tomma vars som skall få data sedan
            String serverResponse;
            String userTyped;

            // Välkomst meddelandet fr severn
            String welcomeMsgFromServer = in.readLine(); // Vill bara läsa 1 gång
            System.out.println(welcomeMsgFromServer);

            // Körs så länge Användaren skriver in något -> vi skickar det till server
            while ((userTyped = userInput.readLine()) != null) {
                ut.println(userTyped);
                System.out.println(timestamp() + " - Sent request: " + userTyped);

                // Response
                serverResponse = in.readLine();
                System.out.println(timestamp() + " - SERVER: " + serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Best practice i en metod annars kanske inte tiden uppdateras!
    private String timestamp() {
        // Timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    public static void main(String[] args) {
        try {
            new Client();
        } catch (UnknownHostException e) {
            // Thrown to indicate that the IP address of a host could not be determined.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
