package org.example.datagrammulticast;

import java.io.IOException;
import java.net.*;

public class DatagramReceiverMulticast {
    // "Edit configurations" -> "DatagramReceiverMulticast"
    // "Modify Options" -> "Allow multiple instances"

    public DatagramReceiverMulticast() throws IOException {
        int portListen = 44444; // måste va samma som Sender

        // Byta ut socket till multicast
        MulticastSocket ds = new MulticastSocket(portListen);

        // spelar inte så jätte stor roll hur stor array, data byte array
        byte[] data = new byte[254];

        // Behöver denna för interfacet
        InetAddress inetAddress = InetAddress.getByName("234.234.234.234");

        // Nätverks interfacet
        InetSocketAddress group = new InetSocketAddress(inetAddress, portListen);

        // kör Sigrun program så vet man namnet, jag har wlan1
        NetworkInterface netIf = NetworkInterface.getByName("eth2");

        ds.joinGroup(group, netIf);

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
            - Casta om byte array till string
            - Har en speciell konstruktor för detta
             */

            String message = new String(dgp.getData(), 0, dgp.getLength());
            System.out.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        new DatagramReceiverMulticast();
    }
}
