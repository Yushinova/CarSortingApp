package org.top.io;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.strategy.FillStrategy;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ManualFill implements FillStrategy {

    private final Scanner reader;

    public ManualFill(Scanner reader) {
        this.reader = reader;
    }

    @Override
    public CustomList fill(CustomList list, int size) {
        for (int i = 0; i < size; i++) {
            boolean success = false;
            while (!success) {
                String brand = customReadString("Input brand:");
                String model = customReadString("Input model:");
                int year = customReadInt("Input year:");
                String color = customReadString("Input color:");
                int power = customReadInt("Input power:");
                double price = customReadDouble("Input price:");
                boolean isNew = customReadBoolean("Input isNew:");

                Car car = new CarBuilder()
                        .setBrand(brand)
                        .setModel(model)
                        .setYear(year)
                        .setColor(color)
                        .setPower(power)
                        .setPrice(price)
                        .setIsNew(isNew)
                        .build();
                if (car != null) {
                    list.add(car);
                    success = true;
                } else {
                    System.out.println("Validation error." + "\nReenter all fields.");
                }
            }
        }
        return list;
    }

    private boolean customReadBoolean(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim().toLowerCase();
            if ("true".equals(line) || "false".equals(line)) {
                return Boolean.parseBoolean(line);
            }
            System.out.println("Value must be true or false!");
        }
    }

    private double customReadDouble(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Value must be a double!");
            }
        }
    }

    private int customReadInt(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Value must be an int!");
            }
        }
    }

    private String customReadString(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
    }
}


