package org.top.builder;

import org.top.model.Car;

import java.time.Year;

public class CarBuilder {
    private String brand;
    private String model;
    private int year;
    private String color;
    private int power;
    private double price;
    private boolean isNew;

    public static CarBuilder from(Car car) {
        return new CarBuilder()
                .setBrand(car.getBrand())
                .setModel(car.getModel())
                .setYear(car.getYear())
                .setColor(car.getColor())
                .setPower(car.getPower())
                .setPrice(car.getPrice())
                .setIsNew(car.isNew());
    }

    public CarBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder setPower(int power) {
        this.power = power;
        return this;
    }

    public CarBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public CarBuilder setIsNew(boolean isNew) {
        this.isNew = isNew;
        return this;
    }

    public Car build() {
        validate();
        return new Car(brand, model, year, color, power, price, isNew);
    }

    private void validate() {
        int currentYear = Year.now().getValue();
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        if (year < 1886) {
            throw new IllegalArgumentException("Year cannot be earlier than 1886");
        }
        if (year > currentYear) {
            throw new IllegalArgumentException("Year cannot be in the future");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        if (power < 0) {
            throw new IllegalArgumentException("Power cannot be negative");
        }
        if (power > 3000) {
            throw new IllegalArgumentException("Power cannot exceed 3000");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
