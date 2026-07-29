package org.top.test.model;

import java.util.ArrayList;
import java.util.List;

import org.top.builder.CarBuilder;
import org.top.comparator.CarComparator;
import org.top.model.Car;
import org.top.test.util.Assert;

public final class SortServiceTest {
    public static void run() {
        testCustomSortingAlgorithm();
    }

    private static void testCustomSortingAlgorithm() {
        List<Car> cars = new ArrayList<>();
        cars.add(new CarBuilder().setBrand("A").setModel("X").setYear(2020).setColor("Red").setPower(300).setPrice(10).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("B").setModel("Y").setYear(2020).setColor("Blue").setPower(100).setPrice(20).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("C").setModel("Z").setYear(2020).setColor("Green").setPower(200).setPrice(30).setIsNew(true).build());

        cars.sort(CarComparator.BY_POWER);

        boolean isSorted = cars.get(0).getPower() == 100 
                && cars.get(1).getPower() == 200 
                && cars.get(2).getPower() == 300;

        Assert.assertThat(isSorted, "Фича 1 (SortService): Проверка корректности работы алгоритма сортировки по возрастанию");
    }
}
