package org.example.genericsdemo;

// Vi vill ha generiskt men vi vill Styra vilka typer som godkänts
// Här kan T vara något som är ett Number, typ float, double, integer, long osv.
public class NumberPrinter <T extends Number> {
    private T someNumber;

    public NumberPrinter(T someNumber) {
        this.someNumber = someNumber;
    }

    public T getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(T someNumber) {
        this.someNumber = someNumber;
    }

    @Override
    public String toString() {
        return "NumberPrinter{" +
                "someNumber = " + someNumber + " - TYPE: " + someNumber.getClass().getSimpleName() +
                '}';
    }


    public static void main(String[] args) {
        NumberPrinter<Float> floatNumberPrinter = new NumberPrinter<>(0.5F);
        System.out.println(floatNumberPrinter);

        NumberPrinter<Double> doubleNumberPrinter = new NumberPrinter<>(0.01337);
        System.out.println(doubleNumberPrinter);

        NumberPrinter<Integer> integerNumberPrinter = new NumberPrinter<>(1337);
        System.out.println(integerNumberPrinter);
    }
}
