package org.top.model;

import org.top.builder.CarBuilder;
import org.top.util.Assert;

public final class BoundaryValidationTest {
    public static void run() {
        testLowerBoundaryYear();
        testUpperBoundaryPower();
    }

    private static void testLowerBoundaryYear() {
        Car exactBoundaryCar = new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(1886).setColor("Red").setPower(200).setPrice(30000.0).setIsNew(false).build();
        Car invalidBoundaryCar = new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(1885).setColor("Red").setPower(200).setPrice(30000.0).setIsNew(false).build();

        Assert.assertThat(exactBoundaryCar != null && invalidBoundaryCar == null, 
                "Фича 9 (Boundary): Валидация нижней границы года выпуска (1886 - ок, 1885 - null)");
    }

    private static void testUpperBoundaryPower() {
        Car exactBoundaryCar = new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(3000).setPrice(45000.0).setIsNew(true).build();
        Car invalidBoundaryCar = new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(3001).setPrice(45000.0).setIsNew(true).build();

        Assert.assertThat(exactBoundaryCar != null && invalidBoundaryCar == null, 
                "Фича 9 (Boundary): Валидация верхней границы мощности (3000 л.с. - ок, 3001 л.с. - null)");
    }
}
