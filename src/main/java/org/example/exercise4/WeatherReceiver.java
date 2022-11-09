package org.example.exercise4;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherReceiver extends JFrame {
    private final JPanel panel = new JPanel();
    private final JTextArea textArea = new JTextArea(20, 30);
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    // Nätverk saker
    private final int port;

    public WeatherReceiver(int port) throws IOException {
        this.port = port;

        // Socket helst skapas utanför loopen där den lyssnar efter ink paket (nedan)
        // port som vi skrev in i Main metod återanvänds för denna DatagramSocket
        //DatagramSocket receiverSocket = new DatagramSocket(port);

        textArea.setLineWrap(true);

        // MULTICAST METOD Adresser från 224.0.0.1 - 239.255.255.255
        MulticastSocket receiverMulticastSocket = new MulticastSocket(port);
        InetAddress inetAddress = InetAddress.getByName("234.234.234.234");
        InetSocketAddress group = new InetSocketAddress(inetAddress, port);
        NetworkInterface netIf = NetworkInterface.getByName("wlan1");
        receiverMulticastSocket.joinGroup(group, netIf);

        // Area + Panel
        textArea.setFont(new Font("Sans-serif", Font.BOLD, 19));
        panel.add(scrollPane);

        // Frame
        this.add(panel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Weather Receiver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // receive Data, UDP
        receiveData(receiverMulticastSocket, textArea);
    }

    private void receiveData(MulticastSocket receiverMulticastSocket, JTextArea textArea) throws IOException {
        // Data som hämtas måste va i en byte array
        byte[] weatherData = new byte[1024];

        // Tid o datum format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String nowTime = LocalDateTime.now().format(formatter);

        // Prep paketet, lite konstigt men måste ha ett paket klart dvs byte[] array
        // Behöver ej va i loopen nedan
        DatagramPacket dgp = new DatagramPacket(weatherData, weatherData.length);

        // Sitter o lyssnar hela tiden
        while (true) {
            // Ta emot inkommande paket till våran port
            //receiverSocket.receive(dgp);

            // Ta emot MULTICAST
            receiverMulticastSocket.receive(dgp);

            // Från vem
            // paketet innehåller också senders IP getAddress()
            System.out.println("\n" + dgp.getAddress().getHostAddress());

            // Packa upp paket från DatagramPacket
            String weatherInfo = new String(dgp.getData(), 0, dgp.getLength());
            String weatherDataString = nowTime + " - " + weatherInfo + "\n";
            System.out.println(weatherDataString);

            // append till JTextArea, GUI SWING
            textArea.append(weatherDataString);
        }
    }

    public static void main(String[] args) throws IOException {
        new WeatherReceiver(44444);
    }
}
