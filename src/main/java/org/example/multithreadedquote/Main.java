package org.example.multithreadedquote;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] quotes = {"Du är vad du äter", "Im the captain now", "Vad heter du ?", "Alex heter jag"};
        // Loopa genom quotes Array och skicka dessa medd till receiver

/*        // Skapar olika trådar för varje iteration, dynamiskt, olika trådar!
        for (int i = 0; i < quotes.length; i++) {
            QuoteSender sender = new QuoteSender(quotes[i]);
            Thread myThread = new Thread(sender);
            myThread.start();
            System.out.println(myThread.getName());
            Thread.sleep(3000);
        }*/

        QuoteSender sender = new QuoteSender();
        QuoteSender sender2 = new QuoteSender();

        // Denna skapar en ny tråd med lambda
        sender.startQuoteMachine();

        // En annan
        sender2.startQuoteMachine();


        // Extra uppgift, lägg receiver i sender klassen och ha som en hybrid, skicka och ta emot samtidigt!

    }
}
