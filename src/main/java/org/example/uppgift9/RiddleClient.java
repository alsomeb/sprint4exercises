package org.example.uppgift9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RiddleClient {
    private final String hostName = "127.0.0.1"; // localhost
    private final int port = 44444;

    public RiddleClient() {
        try(Socket clientSocket = new Socket(hostName, port);
            PrintWriter ut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String fromUser;
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                // tar emot fr server
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("Bye.")) {
                    break; // avslutar loopen, koden körs klart sedan avslutas programmet
                }

                // skicka
                fromUser = input.readLine();
                if(fromUser != null) {
                    ut.println(fromUser);
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't connect to: " + hostName
            + " on port: " + port);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new RiddleClient();
    }
}
