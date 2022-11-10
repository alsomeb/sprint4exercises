package org.example.uppgift15;

/*
Bokstav väljs efter den roll typparametern spelar i den klass där den defineras
E - Element (Listor etc)
K - Key
N - Number
T- Type
V - Value

Vi skall ju ha list kö system så Type passar in här
typen på detta skall ju va lite olika men extends Number (I klassen)
 */

public interface GenericsQueueInterface<T> {

    // Lägger in det i Listan, KÖN
    void put(T element);

    // Tar ut det från KÖN
    T take();

    // size på listan som skall overrides i klassen med impl.
    int size();
}
