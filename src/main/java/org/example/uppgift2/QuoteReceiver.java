package org.example.uppgift2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class QuoteReceiver {

    public QuoteReceiver() throws IOException {
        int port = 40000;
        DatagramSocket ds = new DatagramSocket(port);

        // spelar inte så jätte stor roll hur stor array
        byte[] data = new byte[256];

        while (true) {
            // Prep paketet, lite konstigt men måste ha ett paket klart
            DatagramPacket dgp = new DatagramPacket(data, data.length);

            // Ta emot inkommande paket till våran port
            ds.receive(dgp);

            // Från vem
            // paketet innehåller också senders IP getAddress()
            System.out.println("\n" + dgp.getAddress());

            // Inkommande paket är byte typ så vi måste göra om till String typ
            // getData() == byte array[]
            // offset == börja från 0
            // dgp.getLength() == Returns the length of the data received
            String quote = new String(dgp.getData(), 0, dgp.getLength());
            System.out.println(quote);

            sendKvittens(quote);
        }
    }
    private void sendKvittens(String quote) {
        if(quote != null) {
            try {
                int port = 55555;
                DatagramSocket kvittensSocket = new DatagramSocket();
                InetAddress ip = InetAddress.getLocalHost();
                String message = "Leffe Mottog: " + quote + "\n";
                byte[] data = message.getBytes();
                DatagramPacket kvittensPaket = new DatagramPacket(data, 0, data.length, ip, port);

                // kan jag använda lyssnarens DatagramSocket för att skicka tbx ?
                kvittensSocket.send(kvittensPaket);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new QuoteReceiver();
    }
}
