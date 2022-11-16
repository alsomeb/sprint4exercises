package org.example.uppgift9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RiddleServer {
    private final int port = 44444;

    public RiddleServer() {
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            PrintWriter ut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String outputLine;
            String inputLine;

            RiddleProtocol protocol = new RiddleProtocol();
            outputLine = protocol.processInput(null); // skall va null i början första gång
            System.out.println("Initiated Riddle game");
            ut.println(outputLine); // Triggar igång och skickar gåtan till client

            // Startar konversation med client, så den skickar gåtan först
            while ((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine); // lägger in clients msg i protocol
                System.out.println(outputLine + " skickat");
                ut.println(outputLine); // ger svar till client

                // Kommer från inputLine då client skrivit Bye.
                // Egentligen skall skickas tillbaka till client
                // men vi stoppar här då output som skall skickas är Bye.
                if(outputLine != null && outputLine.equals("Bye.")) {
                    break; // avslutar loopen, koden körs klart sedan avslutas programmet
                }
            }
        } catch (IOException e) {
            System.err.println("Exception caught" +
                    " when listening for a connection on port: " + port);
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new RiddleServer();
    }
}
