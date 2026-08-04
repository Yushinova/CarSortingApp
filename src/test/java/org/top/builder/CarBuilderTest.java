package org.top.builder;

import java.util.List;

import org.top.collection.CustomList;
import org.top.model.Car;
import org.top.util.Assert;

public final class CarBuilderTest {
    public static void run() {
        testValidCarsInCustomCollection();
        testNegativePowerValidation();
        testFutureYearValidation();
        testEmptyBrandValidation();
    }

    private static void testValidCarsInCustomCollection() {
        List<Car> cars = new CustomList<>();

        try {
            Car car1 = new CarBuilder()
                    .setBrand("Toyota")
                    .setModel("Camry")
                    .setYear(2020)
                    .setColor("Red")
                    .setPower(200)
                    .setPrice(30000.0)
                    .setIsNew(false)
                    .build();

            Car car2 = new CarBuilder()
                    .setBrand("BMW")
                    .setModel("X5")
                    .setYear(2022)
                    .setColor("Black")
                    .setPower(250)
                    .setPrice(45000.0)
                    .setIsNew(true)
                    .build();

            cars.add(car1);
            cars.add(car2);

            Assert.assertThat(cars.size() == 2 &&
                            "Toyota".equals(cars.get(0).getBrand()) &&
                            "BMW".equals(cars.get(1).getBrand()),
                    "Фича 5, 8, 9 (CarBuilder): Успешное добавление валидных машин в CustomList");
        } catch (IllegalArgumentException e) {
            Assert.assertThat(false, "Тест провален: " + e.getMessage());
        }
    }

    private static void testNegativePowerValidation() {
        try {
            new CarBuilder()
                    .setBrand("Audi")
                    .setModel("A4")
                    .setYear(2021)
                    .setColor("White")
                    .setPower(-50)
                    .setPrice(35000.0)
                    .setIsNew(false)
                    .build();
            Assert.assertThat(false, "Фича 9 (CarBuilder): Ожидалось исключение для отрицательной мощности");
        } catch (IllegalArgumentException e) {
            Assert.assertThat(true, "Фича 9 (CarBuilder): Отклонение отрицательной мощности");
        }
    }

    private static void testFutureYearValidation() {
        try {
            new CarBuilder()
                    .setBrand("Tesla")
                    .setModel("Model 3")
                    .setYear(2035)
                    .setColor("Red")
                    .setPower(300)
                    .setPrice(60000.0)
                    .setIsNew(true)
                    .build();
            Assert.assertThat(false, "Фича 9 (CarBuilder): Ожидалось исключение для года из будущего");
        } catch (IllegalArgumentException e) {
            Assert.assertThat(true, "Фича 9 (CarBuilder): Отклонение года выпуска из будущего");
        }
    }

    private static void testEmptyBrandValidation() {
        try {
            new CarBuilder()
                    .setBrand("   ")
                    .setModel("Camry")
                    .setYear(2020)
                    .setColor("Silver")
                    .setPower(150)
                    .setPrice(20000.0)
                    .setIsNew(false)
                    .build();
            Assert.assertThat(false, "Фича 9 (CarBuilder): Ожидалось исключение для пустого бренда");
        } catch (IllegalArgumentException e) {
            Assert.assertThat(true, "Фича 9 (CarBuilder): Отклонение пустого имени бренда");
        }
    }
}
