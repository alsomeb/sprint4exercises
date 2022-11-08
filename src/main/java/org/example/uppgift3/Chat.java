package org.example.uppgift3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Olika komponenter och hur man gör actionlyssnare kolla min andra repo
// https://github.com/alsomeb/ExercisesSprint3/tree/master/src/main/java/org/example/morecomponents
public class Chat extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(10, 60);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JButton disconnectBtn = new JButton("Koppla Ner Chatt");
    private JTextField inputField = new JTextField();

    // Multicast
    private InetAddress ip;
    private int port;
    private MulticastSocket multicastSocket;
    private InetSocketAddress group;
    private NetworkInterface netIf = NetworkInterface.getByName("eth2");

    public Chat(int port, String grpAdress) throws IOException {
        this.port = port;
        ip = InetAddress.getByName(grpAdress);
        group = new InetSocketAddress(ip, port);
        multicastSocket = new MulticastSocket(port);
        multicastSocket.joinGroup(group, netIf);
        send("CHATTEN UPPKOPPLAD");

        // Button
        disconnectBtn.addActionListener(this);
        disconnectBtn.setFocusable(false);
        disconnectBtn.setFont(new Font("Sans-serif", Font.BOLD, 18));

        // TextArea
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Sans-serif", Font.BOLD, 18));

        // TextField
        inputField.addActionListener(this);
        inputField.setFont(new Font("Sans-serif", Font.BOLD, 15));
        inputField.setForeground(Color.decode("#E14D2A"));
        inputField.setText("Skriv något");
        inputField.setPreferredSize(new Dimension(600, 50));

        // Panel
        panel.setLayout(new BorderLayout());
        panel.add(disconnectBtn, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);

        // Frame
        this.add(panel);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Alex Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Nätverk Lyssnaren
        receive();

    }

    private void send(String dataString){
        byte[] sendingData = dataString.getBytes();
        DatagramPacket dgp = new DatagramPacket(sendingData, sendingData.length, ip, port);
        try {
            multicastSocket.send(dgp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void receive() {
        byte[] collectedData = new byte[1024];

        while (true) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                String nowTime = LocalDateTime.now().format(formatter);

                DatagramPacket paket = new DatagramPacket(collectedData, collectedData.length);
                multicastSocket.receive(paket);
                String message = new String(paket.getData(), 0, paket.getLength());
                textArea.append(nowTime + ": " + message + "\n");
                System.out.println(nowTime + ": " + message + "\n");

            } catch (IOException e) {
                break;
            }
        }
    }

    // Swing Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(inputField)) {
            send(inputField.getText());
            inputField.setText("");
        }
    }
}
