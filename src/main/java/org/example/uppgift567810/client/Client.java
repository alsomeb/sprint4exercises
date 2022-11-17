package org.example.uppgift567810.client;

import org.example.uppgift567810.server.Intro;
import org.example.uppgift567810.server.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

    private final int port = 55555;

    // Returns the raw IP address string in textual presentation
    private final String ip = InetAddress.getLocalHost().getHostAddress();

    public Client() throws UnknownHostException {

        // Vi använder oss av Object strömmar som vi sedan packar upp och sorterar samt skickar iväg objects
        // Detta istället för PrintWriter som skickade Strings innan
        try(Socket clientSocket = new Socket(ip, port);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream ut = new ObjectOutputStream(clientSocket.getOutputStream());
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)))
        {
            // Skapar upp tomma variablar som skall få data sedan
            // 1 object pga strömmen
            // 1 String pga BufferedReader
            Object serverResponse;
            String userTyped;

            // Vi läser in objects från Server mha ObjectInputStream, sedan sorterar dem
            while ((serverResponse = in.readObject()) != null) {

                // Kan va olika typer av objekt så vi måste ta höjd för detta
                // Object att vi upprättat förbindelse (boolean)
                // Object Response som har en User/Error msg i sig (Container)
                if (serverResponse instanceof Intro intro) {
                    if(intro.isConnected()) {
                        System.out.println("Förbindelse Upprättad");
                    }
                } else if (serverResponse instanceof Response response) {
                    // pattern matching variable "response",
                    // om denna response går att matcha så kan jag använda variabeln!
                    if(response.isFound()) {
                        System.out.println(response.timestampPretty() + " - SERVER: " + response.foundUser().getUserData());
                    } else {
                        // Här blir det null på user i response men vi har en Sträng tillgänglig i objektet error msg, från DB
                        System.out.println(response.timestampPretty() + " - SERVER: " + response.errorMsg());
                    }
                }

                System.out.println("\nSkriv namn på person du vill kolla up:");
                // från buffer reader System.In
                userTyped = userInput.readLine();
                ut.writeObject(userTyped);
                ut.flush();
                System.out.println("Sent request: " + userTyped);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        try {
            new Client();
        } catch (UnknownHostException e) {
            // Thrown to indicate that the IP address of a host could not be determined.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
