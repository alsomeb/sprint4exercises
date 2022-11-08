package org.example.exercise4;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class WeatherReceiver extends JFrame {
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(20, 30);
    private JScrollPane scrollPane = new JScrollPane(textArea);

    // Nätverk saker
    private int port;

    public WeatherReceiver(int port) throws IOException {
        this.port = port;
        DatagramSocket receiverSocket = new DatagramSocket(port);

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
    }

    public static void main(String[] args) throws IOException {
        new WeatherReceiver(44444);
    }
}
