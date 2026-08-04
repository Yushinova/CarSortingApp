package org.top.model;

import org.top.builder.CarBuilder;
import org.top.util.Assert;

public final class BoundaryValidationTest {
    public static void run() {
        testLowerBoundaryYear();
        testUpperBoundaryPower();
    }

    private static void testLowerBoundaryYear() {
        Car exactBoundaryCar = null;
        try {
            exactBoundaryCar = new CarBuilder()
                    .setBrand("Toyota")
                    .setModel("Camry")
                    .setYear(1886)
                    .setColor("Red")
                    .setPower(200)
                    .setPrice(30000.0)
                    .setIsNew(false)
                    .build();
        } catch (IllegalArgumentException e) {
            Assert.assertThat(false, "Год 1886 должен быть валидным, но выброшено исключение: " + e.getMessage());
            return;
        }

        boolean exceptionThrown = false;
        try {
            new CarBuilder()
                    .setBrand("Toyota")
                    .setModel("Camry")
                    .setYear(1885)
                    .setColor("Red")
                    .setPower(200)
                    .setPrice(30000.0)
                    .setIsNew(false)
                    .build();
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        Assert.assertThat(exactBoundaryCar != null && exceptionThrown,
                "Фича 9 (Boundary): Валидация нижней границы года выпуска (1886 - ок, 1885 - исключение)");
    }

    private static void testUpperBoundaryPower() {
        Car exactBoundaryCar = null;
        try {
            exactBoundaryCar = new CarBuilder()
                    .setBrand("BMW")
                    .setModel("X5")
                    .setYear(2022)
                    .setColor("Black")
                    .setPower(3000)
                    .setPrice(45000.0)
                    .setIsNew(true)
                    .build();
        } catch (IllegalArgumentException e) {
            Assert.assertThat(false, "Мощность 3000 должна быть валидной, но выброшено исключение: " + e.getMessage());
            return;
        }

        boolean exceptionThrown = false;
        try {
            new CarBuilder()
                    .setBrand("BMW")
                    .setModel("X5")
                    .setYear(2022)
                    .setColor("Black")
                    .setPower(3001)
                    .setPrice(45000.0)
                    .setIsNew(true)
                    .build();
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        Assert.assertThat(exactBoundaryCar != null && exceptionThrown,
                "Фича 9 (Boundary): Валидация верхней границы мощности (3000 л.с. - ок, 3001 л.с. - исключение)");
    }
}
