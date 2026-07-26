package org.top.model;

public enum CarPreset {
    TOYOTA_CAMRY("Toyota", "Camry", 2020, "Red", 200, 30000, false),
    BMW_X5("BMW", "X5", 2022, "Black", 250, 45000, true),
    AUDI_A4("Audi", "A4", 2021, "White", 190, 35000, false),
    HONDA_CIVIC("Honda", "Civic", 2019, "Blue", 180, 25000, true),
    TOYOTA_COROLLA("Toyota", "Corolla", 2021, "Silver", 140, 22000, true),
    BMW_X3("BMW", "X3", 2021, "Black", 180, 40000, false),
    MERCEDES_E_CLASS("Mercedes", "E-Class", 2018, "White", 220, 50000, false),
    AUDI_Q7("Audi", "Q7", 2022, "Black", 280, 55000, true);

    private final String brand;
    private final String model;
    private final int year;
    private final String color;
    private final int power;
    private final double price;
    private final boolean isNew;

    CarPreset(String brand, String model, int year, String color, int power, double price, boolean isNew) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.power = power;
        this.price = price;
        this.isNew = isNew;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public double getPrice() {
        return price;
    }

    public boolean isNew() {
        return isNew;
    }
}