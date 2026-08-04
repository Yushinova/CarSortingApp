package org.top.io;

import java.util.List;
import java.util.Scanner;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.strategy.FillStrategy;

public class ManualFill implements FillStrategy<Car> {

    private final Scanner reader;

    public ManualFill(Scanner reader) {
        this.reader = reader;
    }

    @Override
    public List<Car> fill(List<Car> list, int size) {
        for (int i = 0; i < size; i++) {
            boolean success = false;
            while (!success) {

                String brand = customReadString("Input brand:");
                if (canceled(brand, list)) return list;
                String model = customReadString("Input model:");
                if (canceled(model, list)) return list;
                Integer year = customReadInt("Input year:");
                if (canceled(year, list)) return list;
                String color = customReadString("Input color:");
                if (canceled(color, list)) return list;
                Integer power = customReadInt("Input power:");
                if (canceled(power, list)) return list;
                Double price = customReadDouble("Input price:");
                if (canceled(price, list)) return list;
                Boolean isNew = customReadBoolean("Input isNew:");
                if (canceled(isNew, list)) return list;
                try {
                    Car car = new CarBuilder()
                            .setBrand(brand)
                            .setModel(model)
                            .setYear(year)
                            .setColor(color)
                            .setPower(power)
                            .setPrice(price)
                            .setIsNew(isNew)
                            .build();
                    list.add(car);
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.err.println("Validation error: " + e.getMessage());
                }

            }
        }
        return list;
    }

    private Boolean canceled(Object value, List<Car> list) {
        if (value == null) {
            list.clear();
            return true;
        }
        return false;
    }

    private Boolean checkCancel(String line) {
        return "cancel".equalsIgnoreCase(line);
    }

    private Boolean customReadBoolean(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim().toLowerCase();
            if (checkCancel(line)) return null;
            if ("true".equals(line) || "false".equals(line)) {
                return Boolean.parseBoolean(line);
            }
            System.out.println("Value must be true or false!");
        }
    }

    private Double customReadDouble(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim();
            if (checkCancel(line)) return null;
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Value must be a double!");
            }
        }
    }

    private Integer customReadInt(String s) {
        while (true) {
            System.out.println(s);
            String line = reader.nextLine().trim();
            if (checkCancel(line)) return null;
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
            if (checkCancel(line)) return null;
            if (!line.isEmpty()) {
                return line;
            }
        }
    }
}


