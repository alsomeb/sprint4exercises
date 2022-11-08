package org.example.multithreadedquote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class QuoteReceiver {
    public QuoteReceiver() {
        int port = 44444;
        try(DatagramSocket receiverSockan = new DatagramSocket(port)) {
            byte[] receivedData = new byte[256];
            DatagramPacket dpg = new DatagramPacket(receivedData, receivedData.length);

            while (true) {
                receiverSockan.receive(dpg);
                String receivedMessage = new String(dpg.getData(), 0, dpg.getLength());

                // Sändarens IP
                System.out.println(dpg.getAddress().getHostAddress());

                // Meddelande decoded
                System.out.println("Message: " + receivedMessage + "\n");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new QuoteReceiver();
    }
}
