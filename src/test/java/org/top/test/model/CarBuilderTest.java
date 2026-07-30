package org.top.test.model;

import java.util.ArrayList;
import java.util.List;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.test.util.Assert;

public final class CarBuilderTest {
    public static void run() {
        testValidCarsInCollection();
        testNegativePowerValidation();
        testFutureYearValidation();
        testEmptyBrandValidation();
    }

    private static void testValidCarsInCollection() {
        List<Car> cars = new ArrayList<>();
        
        Car car1 = new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(250).setPrice(45000).setIsNew(true).build();
        Car car2 = new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(2020).setColor("Red").setPower(200).setPrice(30000).setIsNew(false).build();
        
        if (car1 != null) cars.add(car1);
        if (car2 != null) cars.add(car2);

        Assert.assertThat(cars.size() == 2 && "BMW".equals(cars.get(0).getBrand()) && "Toyota".equals(cars.get(1).getBrand()), 
                "Фича 5, 8, 9 (CarBuilder): Успешное добавление валидных машин в коллекцию");
    }

    private static void testNegativePowerValidation() {
        Car invalidCar = new CarBuilder().setBrand("Audi").setModel("A4").setYear(2021).setColor("White").setPower(-50).setPrice(35000).setIsNew(false).build();
        Assert.assertThat(invalidCar == null, "Фича 9 (CarBuilder): Отклонение отрицательной мощности");
    }

    private static void testFutureYearValidation() {
        Car futureCar = new CarBuilder().setBrand("Tesla").setModel("Model 3").setYear(2035).setColor("Red").setPower(300).setPrice(60000).setIsNew(true).build();
        Assert.assertThat(futureCar == null, "Фича 9 (CarBuilder): Отклонение года выпуска из будущего");
    }

    private static void testEmptyBrandValidation() {
        Car emptyBrandCar = new CarBuilder().setBrand("   ").setModel("Camry").setYear(2020).setColor("Silver").setPower(150).setPrice(20000).setIsNew(false).build();
        Assert.assertThat(emptyBrandCar == null, "Фича 9 (CarBuilder): Отклонение пустого имени бренда");
    }
}
