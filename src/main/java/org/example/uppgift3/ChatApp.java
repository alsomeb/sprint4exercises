package org.example.uppgift3;

import java.io.IOException;

public class ChatApp {
    public static void main(String[] args) {
        try {
            new Chat(44444, "234.234.234.234");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
