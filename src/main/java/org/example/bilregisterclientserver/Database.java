package org.example.bilregisterclientserver;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Database {
    protected List<Car> cars;

    public Database() {
        cars = new ArrayList<>(
                List.of(
                        new Car("ABC123", "Alex Brun", "BMW330", "Black"),
                        new Car("CBA321", "Anki Brun", "Mini Cooper", "White"),
                        new Car("KEX131", "Stefan Brun", "Volvo XC60", "Red"),
                        new Car("KGA661", "Agnes Kihlman", "Volkswagen UP", "Metallic"),
                        new Car("PET331", "Kris Brun", "Lamborghini Aventador", "Yellow")
                )
        );
    }

    // Search by Reg nr
    public Car searchByRegNr(String regNr) {
        return cars.stream()
                .filter(car -> car.getRegNr().equalsIgnoreCase(regNr))
                .findFirst().orElseThrow(() -> new NoSuchElementException("No matching car with this Reg Nr"));
    }
}
