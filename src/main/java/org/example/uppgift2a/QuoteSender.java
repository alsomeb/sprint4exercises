package org.example.uppgift2a;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteSender {

    public QuoteSender() throws IOException, InterruptedException {
        // För att kunna skriva med tangentbordet i terminalen
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress ip = InetAddress.getLocalHost();
        int toPort = 44444;
        DatagramSocket ds = new DatagramSocket();

        String[] quotes = {"Du är vad du äter", "Im the captain now", "Vad heter du ?", "Alex heter jag"};
        int index = quotes.length - 1; // pga indexering
        int counter = 0;

        // Vi skickar quotes dynamiskt, dvs alla i vår quotes array sedan stängs sändaren av!
        while (counter <= index) {
            byte[] data = quotes[counter].getBytes();

            String sentInfo = "Skickat: " + quotes[counter];
            System.out.println(sentInfo);

            // bytearray, börja 0 offset dvs från början, längd på bytearray, sedan ip och port
            DatagramPacket dgp = new DatagramPacket(data, 0, data.length, ip, toPort);

            // DatagramSocket skickar DatagramPacket
            ds.send(dgp);

            Thread.sleep(3000);

            counter++;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new QuoteSender();
    }
}
