package org.example.uppgift15;


// Vi vill ha lite kontroll på denna klass
// Dvs Vi tillåter typer som är ett Number,
// dvs Float, Double, Long, Integer

import java.util.LinkedList;
import java.util.List;

public class GenericQueue <T extends Number> implements GenericsQueueInterface<T> {

    private final List<T> queue = new LinkedList<>();


    // Den bästa antar jag
    // Vi håller oss till double bara!
    // I värsta fall så om denna klass skapas med Number typen måste man
    // Casta om man put() eller köra .value() osv
    public double calcSumOfNumbersInQueue() {
        // om inget finns så return 0.0
        double total = 0.0;

        for (T number : queue) {
            total += number.doubleValue();
        }

        return total;
    }


/*
DENNA ÄR KRÅNGLIG KÖR DEN OVAN

      // Kanske kolla hela listan istället för första element i den!
      //  if (innerList instanceof List<Integer>)
      //  if (innerList<T> instanceof List<Integer>)

    public T calcSumOfNumbersInQueue() throws NullPointerException{
        try {
            if (queue.get(0) instanceof Integer) {
                Integer sum = 0;
                for (T intNumber : queue) {
                    sum += intNumber.intValue();
                }
                return (T) sum;
            }

            if (queue.get(0) instanceof Long) {
                Long sum = 0L;
                for (T longNumber : queue) {
                    sum += longNumber.longValue();
                }
                return (T) sum;
            }

            if (queue.get(0) instanceof Double) {
                Double sum = 0.0;
                for (T doubleNumber : queue) {
                    sum += doubleNumber.doubleValue();
                }
                return (T) sum;
            }
        } catch (IndexOutOfBoundsException ex) {
            // Listan tom
            throw new NullPointerException("List Empty");
        }

        // I alla andra fall
        Integer fel = 0;
        return (T) fel;
    }
*/


    // Från interfacet
    @Override
    public void put(T element) {
        queue.add(element);
    }

    @Override
    public T take() {
        T temp = queue.get(0); // plockar ut den första
        queue.remove(0); // tar bort ur listan så andra element blir pushat fram
        return temp;
    }

    @Override
    public int size() {
        return queue.size();
    }


    @Override
    public String toString() {
        return "GenericQueue{" +
                "queue=" + queue +
                '}';
    }
}
