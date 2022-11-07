package org.example.datagrammulticast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class DatagramSenderMulticast {

    public DatagramSenderMulticast() throws IOException {
        // Behöver ej ha try with resources på denna
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Måste ha multicast adress
        InetAddress ip = InetAddress.getByName("234.234.234.234");
        int toPort = 44444;

        // Socket utbytt till multicast
        MulticastSocket ds = new MulticastSocket();
        String mess;

        // loopen körs så länge jag skriver in ngt i console
        // != null dvs tills blankt
        while ((mess = in.readLine()) != null) {
            byte[] data = mess.getBytes(); // encodes this String into a sequence of bytes

            // bytearray, börja 0 offset dvs från början, längd på bytearray, sedan ip och port
            DatagramPacket dgp = new DatagramPacket(data, 0, data.length, ip, toPort);

            ds.send(dgp);
        }


    }

    public static void main(String[] args) throws IOException {
        new DatagramSenderMulticast();
    }
}
