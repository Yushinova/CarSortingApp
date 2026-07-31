package org.top.sorting;

import java.util.List;

import org.top.builder.CarBuilder;
import org.top.collection.CustomList;
import org.top.comparator.CarComparator;
import org.top.config.GlobalSortConfig;
import org.top.model.Car;
import org.top.strategy.Sorter;
import org.top.util.Assert;

public final class SortServiceTest {
    public static void run() {
        testBubbleSorterDirect();
        testMergeSorterDirect();
        testShuttleSorterDirect();
    }

    private static List<Car> createTestData() {
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(2020).setColor("Red").setPower(200).setPrice(30000.0).setIsNew(false).build());
        cars.add(new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(250).setPrice(45000.0).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("Audi").setModel("A4").setYear(2021).setColor("White").setPower(190).setPrice(35000.0).setIsNew(false).build());
        return cars;
    }

    private static void testBubbleSorterDirect() {
        List<Car> cars = createTestData();
        GlobalSortConfig.getInstance().setSorter(new BubbleSorter<Car>());
        Sorter<Car> sorter = GlobalSortConfig.getInstance().getSorter();
        sorter.sort(cars, CarComparator.BY_POWER, Sorter.Order.DIRECT);

        boolean isSorted = cars.get(0).getPower() == 190 && cars.get(1).getPower() == 200 && cars.get(2).getPower() == 250;
        Assert.assertThat(isSorted, "Фича 1 (SortService): Интеграция BubbleSorter коллег с CustomList");
    }

    private static void testMergeSorterDirect() {
        List<Car> cars = createTestData();
        GlobalSortConfig.getInstance().setSorter(new MergeSorter<Car>());
        Sorter<Car> sorter = GlobalSortConfig.getInstance().getSorter();
        sorter.sort(cars, CarComparator.BY_POWER, Sorter.Order.DIRECT);

        boolean isSorted = cars.get(0).getPower() == 190 && cars.get(1).getPower() == 200 && cars.get(2).getPower() == 250;
        Assert.assertThat(isSorted, "Фича 1 (SortService): Интеграция MergeSorter коллег с CustomList");
    }

    private static void testShuttleSorterDirect() {
        List<Car> cars = createTestData();
        GlobalSortConfig.getInstance().setSorter(new ShuttleSorter<Car>());
        Sorter<Car> sorter = GlobalSortConfig.getInstance().getSorter();
        sorter.sort(cars, CarComparator.BY_POWER, Sorter.Order.DIRECT);

        boolean isSorted = cars.get(0).getPower() == 190 && cars.get(1).getPower() == 200 && cars.get(2).getPower() == 250;
        Assert.assertThat(isSorted, "Фича 1 (SortService): Интеграция ShuttleSorter коллег с CustomList");
    }
}
