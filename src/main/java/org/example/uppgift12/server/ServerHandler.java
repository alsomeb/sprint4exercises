package org.example.uppgift12.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler {
    private Socket socket;

    // Lista av alla clients ut ström
    static private final List<ObjectOutputStream> listOfConnectedClientsOutStream = new ArrayList<>();

    // klassobjektets egna strömmar (På tråden)
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerHandler(Socket socket) throws IOException, ClassNotFoundException {
        this.socket = socket;
        // out måste bindas först! annars funkar inte detta
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        // när vi har define strömmarna kan vi spara dem i en statisk lista
        listOfConnectedClientsOutStream.add(out);
        handler();
    }

    private void handler() throws IOException, ClassNotFoundException {
            Object clientMessage;
            while ((clientMessage = in.readObject()) != null) {
                System.out.println(clientMessage);

                // loopar bara igenom statiska listan som har alla clients strömmar configurerade och klara
                // så det ända jag behöver göra är att writeObject(msg)
                for (ObjectOutputStream clientSocket : listOfConnectedClientsOutStream) {
                    clientSocket.writeObject(clientMessage);
                }
            }

    }
}

