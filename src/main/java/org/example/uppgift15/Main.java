package org.example.uppgift15;

public class Main {
    public static void main(String[] args) {
        GenericQueue<Integer> integerQueue = new GenericQueue<>();
        System.out.println("Antal I Kön: " + integerQueue.size());

        // Lägger in i Kön
        integerQueue.put(23);
        integerQueue.put(25);
        integerQueue.put(30);

        System.out.println(integerQueue);
        System.out.println("Antal I Kön: " + integerQueue.size());

        // Ta ut ett element och beta av kön.
        int first = integerQueue.take();
        System.out.println("Första i kön: " + first);

        // Kolla av Kön
        System.out.println(integerQueue);
        System.out.println("Antal I Kön: " + integerQueue.size());

        // Summera resterande i kön
        double sum = integerQueue.calcSumOfNumbersInQueue();
        System.out.println(sum);


        System.out.println();

        // Provar Double typ
        GenericQueue<Double> doubleGenericQueue = new GenericQueue<>();
        System.out.println("Double kö size: " + doubleGenericQueue.size());
        //double sumTestEmpty = doubleGenericQueue.calcSumOfNumbersInQueue();

        doubleGenericQueue.put(23.5);
        doubleGenericQueue.put(25.5);
        System.out.println(doubleGenericQueue);
        System.out.println("Double kö size: " + doubleGenericQueue.size());

        double sum2 = doubleGenericQueue.calcSumOfNumbersInQueue();
        System.out.println(sum2);

        System.out.println();

        // Number, blir lite krångligt faktiskt
        GenericQueue<Number> numberGenericQueue = new GenericQueue<>();
        System.out.println(numberGenericQueue);
        System.out.println("Summa: " + numberGenericQueue.calcSumOfNumbersInQueue());

        numberGenericQueue.put(3.0F);
        numberGenericQueue.put(3);
        numberGenericQueue.put(10.5);
        System.out.println(numberGenericQueue);

        float first2 = numberGenericQueue.take().floatValue();
        System.out.println("Första i kön: " + first2);

        System.out.println("Number kö size: " + numberGenericQueue.size());
        System.out.println("Summa: " + numberGenericQueue.calcSumOfNumbersInQueue());

    }
}
