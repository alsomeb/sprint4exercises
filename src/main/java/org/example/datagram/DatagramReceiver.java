package org.example.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceiver {

    public DatagramReceiver() throws IOException {
        int portListen = 44444; // måste va samma som Sender
        DatagramSocket ds = new DatagramSocket(portListen);

        byte[] data = new byte[254]; // spelar inte så jätte stor roll hur stor array

        while (true) {
            // Prep paketet, lite konstigt men måste ha ett paket klart
            // När ett paket kommer så kommer innehållet läggas i detta paket
            // Annan constructor
            DatagramPacket dgp = new DatagramPacket(data, data.length);

            // Ta emot
            ds.receive(dgp);

            // Från vem
            System.out.println("\n" + dgp.getAddress()); // paketet innehåller också senders IP

            /*
            bytes – The bytes to be decoded into characters
            offset – The index of the first byte to decode
            length – The number of bytes to decode

            - Casta om byte array till string
            - Har en speciell konstruktor för detta
             */

            String message = new String(dgp.getData(), 0, dgp.getLength());
            System.out.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new DatagramReceiver();
    }
}
