package org.example.uppgift2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteSender {

    public QuoteSender() throws IOException, InterruptedException {
        // För att kunna skriva med tangentbordet i terminalen
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress ip = InetAddress.getLocalHost();
        int toPort = 40000;

        // Uppskapandet av socket globalt, ej i scopet, men dem kan användas där samtidigt i loopen!
        DatagramSocket ds = new DatagramSocket();
        DatagramSocket kvittensSocket = new DatagramSocket(55555);

        String[] quotes = {"Du är vad du äter", "Im the captain now", "Vad heter du ?", "Alex heter jag"};
        int index = quotes.length - 1; // pga indexering
        int counter = 0;

        // Vi skickar quotes dynamiskt, dvs alla i vår quotes array sedan stängs sändaren av!
        while (counter <= index) {
            byte[] data = quotes[counter].getBytes();

            String sentInfo = "Skickat: " + quotes[counter];
            System.out.println(sentInfo);

            // bytearray, börja 0 offset dvs från början, längd på bytearray, sedan ip och port
            DatagramPacket dgp = new DatagramPacket(data, 0, data.length, ip, toPort);

            // DatagramSocket skickar DatagramPacket
            ds.send(dgp);

            // Skicka varje 3 sek
            //Thread.sleep(3000);

            // Måste ha en annan receiver med annan port
            receiveKvittens(kvittensSocket);

            counter++;
        }
    }

    private void receiveKvittens(DatagramSocket kvittensSocket) throws IOException {
        // Detta hamnar i while loopen
        // Socketarna får inte skapas i loopen bara refereras, annars får man i detta fall Binding Exception, dem låser sig!

        byte[] data = new byte[256];
        DatagramPacket dgp = new DatagramPacket(data, data.length);

        // Ta emot inkommande kvittensPaket till våran port
        kvittensSocket.receive(dgp);

        // Från vem
        // paketet innehåller också senders IP getAddress()
        System.out.println("\n" + dgp.getAddress());

        // Inkommande paket är byte typ så vi måste göra om till String typ
        // getData() == byte array[]
        // offset == börja från 0
        // dgp.getLength() == Returns the length of the data received
        String kvittoMedd = new String(dgp.getData(), 0, dgp.getLength());
        System.out.println(kvittoMedd);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new QuoteSender();
    }
}
