package org.example.bilregistermulticlient;

import org.example.bilregisterclientserver.Car;
import org.example.bilregisterclientserver.Database;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;


public class ClientHandler implements Runnable{
    private final String welcomeMsg = "Welcome to our Car Database, please enter a Reg Nr to search:";

    private final Database carDatabase = new Database();

    private final Socket client;

    public ClientHandler(Socket socket) {
        this.client = socket;

    }

    private void searchByRegNr(String request, PrintWriter ut) {
        try {
            Car foundCar = carDatabase.searchByRegNr(request.trim());
            ut.println(foundCar.getRegNr() + " " + foundCar.getOwner() + " " + foundCar.getBrand() + " " + foundCar.getColor());
        } catch (NoSuchElementException e) {
            ut.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try(PrintWriter ut = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
        {
            ut.println(welcomeMsg);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
