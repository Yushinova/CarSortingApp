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
        
        Car car1 = new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(2020).setColor("Red").setPower(200).setPrice(30000.0).setIsNew(false).build();
        Car car2 = new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(250).setPrice(45000.0).setIsNew(true).build();
        
        if (car1 != null) cars.add(car1);
        if (car2 != null) cars.add(car2);

        Assert.assertThat(cars.size() == 2 && "Toyota".equals(cars.get(0).getBrand()) && "BMW".equals(cars.get(1).getBrand()), 
                "Фича 5, 8, 9 (CarBuilder): Успешное добавление валидных машин в CustomList");
    }

    private static void testNegativePowerValidation() {
        Car invalidCar = new CarBuilder().setBrand("Audi").setModel("A4").setYear(2021).setColor("White").setPower(-50).setPrice(35000.0).setIsNew(false).build();
        Assert.assertThat(invalidCar == null, "Фича 9 (CarBuilder): Отклонение отрицательной мощности");
    }

    private static void testFutureYearValidation() {
        Car futureCar = new CarBuilder().setBrand("Tesla").setModel("Model 3").setYear(2035).setColor("Red").setPower(300).setPrice(60000.0).setIsNew(true).build();
        Assert.assertThat(futureCar == null, "Фича 9 (CarBuilder): Отклонение года выпуска из будущего");
    }

    private static void testEmptyBrandValidation() {
        Car emptyBrandCar = new CarBuilder().setBrand("   ").setModel("Camry").setYear(2020).setColor("Silver").setPower(150).setPrice(20000.0).setIsNew(false).build();
        Assert.assertThat(emptyBrandCar == null, "Фича 9 (CarBuilder): Отклонение пустого имени бренда");
    }
}
