package org.top.test.model;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.test.util.Assert;

public final class BoundaryValidationTest {
    public static void run() {
        testLowerBoundaryYear();
        testUpperBoundaryPower();
    }

    private static void testLowerBoundaryYear() {
        Car exactBoundaryCar = new CarBuilder().setBrand("A").setModel("X").setYear(1886).setColor("Black").setPower(100).setPrice(10).setIsNew(false).build();
        Car invalidBoundaryCar = new CarBuilder().setBrand("A").setModel("X").setYear(1885).setColor("Black").setPower(100).setPrice(10).setIsNew(false).build();

        Assert.assertThat(exactBoundaryCar != null && invalidBoundaryCar == null, 
                "Фича 9 (Boundary): Валидация нижней границы года выпуска (1886 - ок, 1885 - null)");
    }

    private static void testUpperBoundaryPower() {
        Car exactBoundaryCar = new CarBuilder().setBrand("A").setModel("X").setYear(2020).setColor("Black").setPower(3000).setPrice(10).setIsNew(false).build();
        Car invalidBoundaryCar = new CarBuilder().setBrand("A").setModel("X").setYear(2020).setColor("Black").setPower(3001).setPrice(10).setIsNew(false).build();

        Assert.assertThat(exactBoundaryCar != null && invalidBoundaryCar == null, 
                "Фича 9 (Boundary): Валидация верхней границы мощности (3000 л.с. - ок, 3001 л.с. - null)");
    }
}
