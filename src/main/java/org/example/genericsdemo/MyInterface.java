package org.example.genericsdemo;

// Går bra att göra generiska interface också
// Kan va <T> också spelar ingen roll
public interface MyInterface <E> {
    void setContent(E content);
    E anotherMethod(E string);
}
