package org.top.sorting;

import java.util.List;

import org.top.builder.CarBuilder;
import org.top.collection.CustomList;
import org.top.comparator.CarComparator;
import org.top.model.Car;
import org.top.strategy.Sorter;
import org.top.util.Assert;

public final class EvenOddSortTest {
    public static void run() {
        testPowerSublistSorter();
        testYearSublistSorter();
    }

    private static void testPowerSublistSorter() {
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(2020).setColor("Red").setPower(251).setPrice(30000.0).setIsNew(false).build());
        cars.add(new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(300).setPrice(45000.0).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("Audi").setModel("A4").setYear(2021).setColor("White").setPower(183).setPrice(35000.0).setIsNew(false).build());
        cars.add(new CarBuilder().setBrand("Honda").setModel("Civic").setYear(2019).setColor("Blue").setPower(140).setPrice(25000.0).setIsNew(true).build());

        BubbleSorter<Car> sorter = new BubbleSorter<>();
        
        PowerSublistCarSorter sublistSorter = new PowerSublistCarSorter(sorter, AbstractSublistCarSorter.Filter.EVEN);
        sublistSorter.sort(cars, CarComparator.BY_POWER, Sorter.Order.DIRECT);

        boolean conditionOdd = cars.get(0).getPower() == 251 && cars.get(2).getPower() == 183;
        boolean conditionEven = cars.get(1).getPower() == 140 && cars.get(3).getPower() == 300;

        Assert.assertThat(conditionOdd && conditionEven, "Фича 11, 12 (Доп. 1): Работа декоратора PowerSublistCarSorter группы");
    }

    private static void testYearSublistSorter() {
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder().setBrand("Toyota").setModel("Camry").setYear(2021).setColor("Red").setPower(200).setPrice(30000.0).setIsNew(false).build());
        cars.add(new CarBuilder().setBrand("BMW").setModel("X5").setYear(2022).setColor("Black").setPower(250).setPrice(45000.0).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("Audi").setModel("A4").setYear(2019).setColor("White").setPower(190).setPrice(35000.0).setIsNew(false).build());
        cars.add(new CarBuilder().setBrand("Honda").setModel("Civic").setYear(2020).setColor("Blue").setPower(180).setPrice(25000.0).setIsNew(true).build());

        BubbleSorter<Car> sorter = new BubbleSorter<>();
        
        YearSublistCarSorter sublistSorter = new YearSublistCarSorter(sorter, AbstractSublistCarSorter.Filter.EVEN);
        sublistSorter.sort(cars, CarComparator.BY_YEAR, Sorter.Order.DIRECT);

        boolean conditionOdd = cars.get(0).getYear() == 2021 && cars.get(2).getYear() == 2019;
        boolean conditionEven = cars.get(1).getYear() == 2020 && cars.get(3).getYear() == 2022;

        Assert.assertThat(conditionOdd && conditionEven, "Фича 11, 12 (Доп. 1): Работа декоратора YearSublistCarSorter группы");
    }
}
