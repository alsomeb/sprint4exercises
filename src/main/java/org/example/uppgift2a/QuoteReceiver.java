package org.example.uppgift2a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class QuoteReceiver {

    public QuoteReceiver() throws IOException {
        int port = 44444;
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
        }
    }

    public static void main(String[] args) throws IOException {
        new QuoteReceiver();
    }
}
