package org.example.multithreadedquote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteSender {
    private final String[] quotes = {"Du är vad du äter", "Im the captain now", "Vad heter du ?", "Alex heter jag"};

    // Lambda kan användas pga när man gör en anonym klass så blir de som en runnable interface
    // Runnable är ett functional interface dvs 1 metod bara
    // vi skriver bara new Thread() anonymt
    public void startQuoteMachine() {
        new Thread(() -> {
            int count = 0;
            int total = quotes.length - 1;
            while (count <= total) {
                int port = 44444;

                try (DatagramSocket senderSocket = new DatagramSocket()) {
                    // the address of the local host.
                    InetAddress ip = InetAddress.getLocalHost();

                    // Konvertera message till bytes[]
                    //byte[] dataToBeSent = message.getBytes();
                    byte[] dataToBeSent = quotes[count].getBytes();

                    // Logg
                    System.out.println("Sent message: " + quotes[count] + " (THREAD: " + Thread.currentThread().getName() + ")" + "\n");

                    // Paketet som skall skickas
                    DatagramPacket dgp = new DatagramPacket(dataToBeSent, 0, dataToBeSent.length, ip, port);

                    // Skicka!
                    senderSocket.send(dgp);

                    // Sleep
                    Thread.sleep(3000);

                    // Increment shit
                    count++;

                } catch (IOException ex) {
                    // if the socket could not be opened, or the socket could not be bound.
                    System.out.println(ex.getMessage());
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

}
