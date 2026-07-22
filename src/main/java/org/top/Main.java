package org.top;

import org.top.builder.CarBuilder;
import org.top.comparator.CarComparator;
import org.top.model.Car;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000, false));
        cars.add(new Car("BMW", "X5", 2022, "Black", 250, 45000, true));
        cars.add(new Car("Audi", "A4", 2021, "White", 190, 35000, false));
        cars.add(new Car("Honda", "Civic", 2019, "Blue", 180, 25000, true));
        cars.add(new Car("Toyota", "Corolla", 2021, "Silver", 140, 22000, true));
        cars.add(new Car("BMW", "X3", 2021, "Black", 180, 40000, false));
        cars.add(new Car("Mercedes", "E-Class", 2018, "White", 220, 50000, false));
        cars.add(new Car("Audi", "Q7", 2022, "Black", 280, 55000, true));

        System.out.println("___Исходный список___");
        cars.forEach(System.out::println);

        System.out.println("___Отсортированный список по мощности___");
        cars.stream()
                .sorted(CarComparator.BY_POWER)
                .forEach(System.out::println);

        System.out.println("___Отсортированный список старых по году по убыванию___");
        cars.stream()
                .filter(car -> !car.isNew())
                .sorted(CarComparator.BY_YEAR.reversed())//можно прям в стриме вызывать, если нужен другой порядок
                .forEach(System.out::println);

        System.out.println("___Отсортированный список новых по бренду/цене___");
        cars.stream()
                .filter(Car::isNew)
                .sorted(CarComparator.BY_BRAND.thenComparing(CarComparator.BY_PRICE))
                .forEach(System.out::println);
    }
}