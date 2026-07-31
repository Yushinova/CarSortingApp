package org.top.model;

import org.top.builder.CarBuilder;
import org.top.comparator.CarComparator;
import org.top.util.Assert;

public final class CarComparatorTest {
    public static void run() {
        testPowerComparator();
    }

    private static void testPowerComparator() {
        Car weakCar = new CarBuilder().setBrand("A").setModel("A").setYear(2020).setColor("Red").setPower(100).setPrice(10).setIsNew(true).build();
        Car strongCar = new CarBuilder().setBrand("B").setModel("B").setYear(2020).setColor("Blue").setPower(300).setPrice(20).setIsNew(true).build();

        int compareResult = CarComparator.BY_POWER.compare(weakCar, strongCar);
        Assert.assertThat(compareResult < 0, "Фича 10 (CarComparator): Сверка констант компараторов группы");
    }
}
