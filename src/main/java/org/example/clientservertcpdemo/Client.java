package org.example.clientservertcpdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public Client() {
        // Sitter man på samma nätverk kan man skicka till varandras datorer
        try(Socket sock = new Socket("127.0.0.1", 55555);
            // Auto flushad!! Så det inte fastnar DATA, väldigt vanligt
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true))
        {
            String message = "Tjena från client!";
            while (true) {
                out.println(message);
                System.out.println("Sending: " + message);
                Thread.sleep(2000);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An I/O error occurred when opening the socket.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}
