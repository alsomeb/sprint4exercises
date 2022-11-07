package org.example.datagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 - Kan gå in på edit configurations på DatagramSenderMulticast
 -> "Modify Options -> "Allot Multiple Instances"

 - Dvs man kan va flera Senders men inte receivers
 - Har man flera receivers då får man "Bind Exception" på porten,
 den används ju redan av en app, dvs vår receiver

 - Om man vill ha flera mottagare som lyssnar på en sändare:
 - MultiCast (lite som en radio, plocka upp dem medd man vill, bara tuna in)
 */


public class DatagramSender {

    public DatagramSender() throws IOException {
        // Behöver ej ha try with resources på denna
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress ip = InetAddress.getLocalHost();
        int toPort = 44444;

        DatagramSocket ds = new DatagramSocket();
        String mess;

        // loopen körs så länge jag skriver in ngt i console
        // != null dvs tills blankt
        while ((mess = in.readLine()) != null) {
            byte[] data = mess.getBytes(); // encodes this String into a sequence of bytes

            // bytearray, börja 0 offset dvs från början, längd på bytearray, sedan ip och port
            DatagramPacket dgp = new DatagramPacket(data, 0, data.length, ip, toPort);

            // DatagramSocket skickar DatagramPacket
            /*
            Sends a datagram packet from this socket.
            The DatagramPacket includes information indicating the data to be sent,
            its length, the IP address of the remote host,
            and the port number on the remote host.
             */
            ds.send(dgp);
        }


    }

    public static void main(String[] args) throws IOException {
        new DatagramSender();
    }
}
