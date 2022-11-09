package org.example.genericsdemo;

// Parameter T (Type), okänd klass som skall defineras när man skapar upp
// Kan ha flera med "," <T, ID> som i spring
// I koden använder vi parameter namnet ist för klass namnet annars vi hade haft
// Man gör kanske inte egna Generiska klasser ofta, men Java har många som vi använder dagligen!
public class Box <T> {

    private T contentOfBox;

    // getter
    public T getContentOfBox() {
        return contentOfBox;
    }

    // setter
    public void setContentOfBox(T contentOfBox) {
        this.contentOfBox = contentOfBox;
    }

    @Override
    public String toString() {
        return "Box{" +
                "contentOfBox=" + contentOfBox +
                '}';
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setContentOfBox("String Låda");

        Box<Integer> integerBox = new Box<>();
        integerBox.setContentOfBox(23);

        // En Box av Typen Box som tar en Integer
        Box<Box<Integer>> metaBox = new Box<>();
        metaBox.setContentOfBox(integerBox);

        System.out.println(stringBox);
        System.out.println(integerBox);
        System.out.println(metaBox);
    }
}
