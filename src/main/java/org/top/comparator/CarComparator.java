package org.top.comparator;

import java.util.Comparator;

import org.top.model.Car;

public class CarComparator {
    public static final Comparator<Car> BY_BRAND =
            Comparator.comparing(Car::getBrand);

    public static final Comparator<Car> BY_MODEL =
            Comparator.comparing(Car::getModel);

    public static final Comparator<Car> BY_YEAR =
            Comparator.comparingInt(Car::getYear);

    public static final Comparator<Car> BY_COLOR =
            Comparator.comparing(Car::getColor);

    public static final Comparator<Car> BY_POWER =
            Comparator.comparingInt(Car::getPower);

    public static final Comparator<Car> BY_PRICE =
            Comparator.comparingDouble(Car::getPrice);

    public static final Comparator<Car> BY_IS_NEW =
            Comparator.comparing(Car::isNew);
}

