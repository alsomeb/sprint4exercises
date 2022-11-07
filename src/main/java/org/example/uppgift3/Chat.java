package org.example.uppgift3;

import javax.swing.*;
import java.awt.*;

// Olika komponenter och hur man gör actionlyssnare kolla min andra repo
// https://github.com/alsomeb/ExercisesSprint3/tree/master/src/main/java/org/example/morecomponents
public class Chat extends JFrame {
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(10, 60);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JButton disconnectBtn = new JButton("Koppla Ner Chatt");
    private JTextField inputField = new JTextField();

    public Chat() {
        // Button
        disconnectBtn.setFocusable(false);
        disconnectBtn.setFont(new Font("Sans-serif", Font.BOLD, 18));

        // TextArea
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Sans-serif", Font.BOLD, 18));

        // TextField
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
    }
}
