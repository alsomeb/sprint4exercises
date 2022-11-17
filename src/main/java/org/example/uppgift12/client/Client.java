package org.example.uppgift12.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {
    private final JPanel panel = new JPanel();
    private final JTextArea textArea = new JTextArea(10, 60);
    private final JScrollPane scrollPane = new JScrollPane(textArea);
    private final JButton disconnectBtn = new JButton("Koppla Ner Chatt");
    private final JTextField inputField = new JTextField();
    private final String chatName;

    // Networking
    private final int port = 55555;
    private final Socket clientSocket;

    private final ObjectInputStream in;
    private final ObjectOutputStream out;


    public Client(String chatName) throws IOException, ClassNotFoundException {
        this.chatName = chatName;
        clientSocket = new Socket("127.0.0.1", port);

        // ALLTID BINDA UTSTRÖM FÖRE INSTRÖM!!
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

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
        inputField.setText("Skriv");
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
        this.setTitle(chatName + " - Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sendMsg("Hej hej");

        listenForMsg();
    }

    private void listenForMsg() throws IOException, ClassNotFoundException {
        Object serverResponse;
        while ((serverResponse = in.readObject()) != null) {
            textArea.append(serverResponse + "\n");
        }
    }

    private void sendMsg(String message) throws IOException {
        out.writeObject(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputField) {
            try {
                sendMsg(chatName + ": " + inputField.getText().trim());
                inputField.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
