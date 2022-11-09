package org.example.uppgift2kvittenskorrekt;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Sender {
    final static String[] quotes = {"Du är vad du äter", "Im the captain now", "Vad heter du ?", "Alex heter jag"};


    public static void main(String[] args) throws IOException, InterruptedException {
        int index = quotes.length - 1; // pga indexering
        int counter = 0;

        InetAddress toAdr = InetAddress.getLocalHost();
        int sendPort = 55555;
        int recPort = 55556;
        DatagramSocket socket = new DatagramSocket(recPort);

        while(counter <= index){
            byte[] data = quotes[counter].getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, sendPort);
            socket.send(packet);


            // behövde skapa en ny byte array för receive, annars kapade den bokstäver i svaret
            byte[] dataReceived = new byte[256];
            packet = new DatagramPacket(dataReceived, dataReceived.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);

            Thread.sleep(3000);
            counter++;
        }
    }
}
