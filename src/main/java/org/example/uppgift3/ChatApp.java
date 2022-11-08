package org.example.uppgift3;

import javax.swing.*;
import java.io.IOException;

public class ChatApp {
    public static void main(String[] args) {
        String chatName = prompt("Ange Chat Namn");

        try {
            new Chat(44444, "234.234.234.234", chatName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kan ej starta chat appen");
        }
    }

    public static String prompt(String messageInPrompt) {
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
}

