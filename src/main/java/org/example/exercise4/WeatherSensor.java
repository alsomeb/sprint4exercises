package org.example.exercise4;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class WeatherSensor {

    public WeatherSensor(){
        while (true) {
            String cityName = prompt("Ange Stad");
            String temperatur = prompt("Ange temperatur just nu");
            System.out.println(cityName);
            System.out.println(temperatur);

            String weatherDataString = cityName + " Temp: " + temperatur;
            sendWeatherData(weatherDataString);
        }


    }

    private String prompt(String messageInPrompt) {
        while (true) {
            String city = JOptionPane.showInputDialog(messageInPrompt);
            if (city == null) {
                // Klickar man cancel så blir String city == null dvs avslutar programmet
                System.exit(0);
            } else if (city.isBlank()) {
                JOptionPane.showMessageDialog(null, "Får ej va tomt!");
            } else {
                return city;
            }
        }
    }

    private void sendWeatherData(String city){
        try {
            //InetAddress ip = InetAddress.getLocalHost();

            // MULTICAST IP, Adresser från 224.0.0.1 - 239.255.255.255
            InetAddress inetAddress = InetAddress.getByName("234.234.234.234");
            int port = 44444;
            //DatagramSocket senderSocket = new DatagramSocket();

            // MULTICAST METOD
            MulticastSocket senderMulticastSocket = new MulticastSocket();

            byte[] weatherDataToSend = city.getBytes();
            DatagramPacket weatherPacket = new DatagramPacket(weatherDataToSend, 0, weatherDataToSend.length, inetAddress, port);

            //senderSocket.send(weatherPacket);
            senderMulticastSocket.send(weatherPacket);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Gick ej skicka väder data");
        } catch (IllegalArgumentException exArg) {
            JOptionPane.showMessageDialog(null, exArg.getMessage() + "\nChange port Nr!");
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new WeatherSensor();
    }
}
