package org.example.uppgift5;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;

public class FakeDatabase {
    private final List<User> people;

    public FakeDatabase() {
        people = List.of(
                new User("Alexander Brun", "Testgatan 13", "0792515151", LocalDate.of(1991, Month.AUGUST, 23)),
                new User("Gustav Von Lingon", "Sturegatan 55", "063222222", LocalDate.of(1991, Month.SEPTEMBER, 15)),
                new User("Kurre Koben", "Babystreet 23", "072515155", LocalDate.of(2005, Month.JANUARY, 5)),
                new User("Andreas Tiffen", "Jakan 123", "0712452525", LocalDate.of(1992, Month.OCTOBER, 23))
        );
    }

    public User findByFullName(String name) {
        return people.stream()
                .filter(user -> user.getFullName().equalsIgnoreCase(name.trim()))
                .findFirst().orElseThrow(() -> new NoSuchElementException("User With That Name Doesnt Exist!"));
    }
}
