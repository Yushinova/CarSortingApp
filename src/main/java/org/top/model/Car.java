package org.top.model;

import java.util.Objects;

public class Car {
    private final String brand;
    private final String model;
    private final int year;
    private final String color;
    private final int power;
    private final double price;
    private final boolean isNew;

    public Car(String brand, String model, int year, String color, int power, double price, boolean isNew) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.power = power;
        this.price = price;
        this.isNew = isNew;
    }

    public String getBrand() {return brand;}

    public String getModel() {return model;}

    public int getYear() {return year;}

    public String getColor() {return color;}

    public int getPower() {
        return power;
    }

    public double getPrice() {return price;}

    public boolean isNew() {return isNew;}

    @Override
    public String toString() {
        return String.format("%s %s (%d) | %s | %d л.с. | %.2f ₽ | %s",
                brand,
                model,
                year,
                color,
                power,
                price,
                isNew ? "Новая" : "Б/У"
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return year == car.year
                && power == car.power
                && Double.compare(price, car.price) == 0
                && isNew == car.isNew
                && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model)
                && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, color, power, price, isNew);
    }
}
