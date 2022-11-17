package org.example.uppgift12.client;

import javax.swing.*;
import java.io.IOException;

public class ClientRun {
    public static void main(String[] args) {
        String chatName = prompt("Ange Chat Namn");

        try {
            new Client(chatName);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Kan ej starta chat appen");
        }
    }

    public static String prompt(String messageInPrompt) {
        while (true) {
            String name = JOptionPane.showInputDialog(messageInPrompt);
            if (name == null) {
                // Klickar man cancel så blir String city == null dvs avslutar programmet
                System.exit(0);
            } else if (name.isBlank()) {
                JOptionPane.showMessageDialog(null, "Namn Får ej va tomt!");
            } else {
                return name;
            }
        }
    }
}
