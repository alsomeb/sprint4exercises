package org.example.exercise2kvittenskorrekt;


import java.io.IOException;
import java.net.*;

public class Receiver {
    public static void main(String[] args) throws IOException {
        InetAddress toAdr = InetAddress.getLocalHost();
        int recPort = 55555;
        int sendPort = 55556;
        DatagramSocket socket = new DatagramSocket(recPort);
        byte[] data = new byte[100];

        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);

            String kvittens = "meddelande mottaget";
            byte[] kvittensBA = kvittens.getBytes();
            packet = new DatagramPacket(kvittensBA, kvittensBA.length, toAdr, sendPort);
            socket.send(packet);
            System.out.println("kvittens sent");
        }
    }
}


