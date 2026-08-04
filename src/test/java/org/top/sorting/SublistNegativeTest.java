package org.top.sorting;

import org.top.builder.CarBuilder;
import org.top.collection.CustomList;
import org.top.comparator.CarComparator;
import org.top.model.Car;
import org.top.strategy.Sorter;
import org.top.util.Assert;
import org.top.sorting.AbstractSublistCarSorter.Filter;

import java.util.List;

public final class SublistNegativeTest {
    public static void run() {
        nullListBehavior();
        nullElementBehavior();
        nullFilterBehavior();
        nullSorterBehavior();
    }

    private static void nullListBehavior(){
        List<Car> cars = null;
        boolean result = false;
        Sorter<Car> sorter = new PowerSublistCarSorter(new BubbleSorter<>(), Filter.ODEN);
        try {
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result, "(SublistNegative): обработка null списка");
    }

    private static void nullElementBehavior(){
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder()
                .setBrand("Chevrolet")
                .setModel("Cyan")
                .setYear(2010)
                .setColor("White")
                .setPower(108)
                .setPrice(1300000)
                .setIsNew(true)
                .build()
        );
        cars.add(null);
        cars.add(new CarBuilder()
                .setBrand("Chevrolet")
                .setModel("Cyan")
                .setYear(2008)
                .setColor("White")
                .setPower(98)
                .setPrice(1100000)
                .setIsNew(true)
                .build()
        );
        boolean result = true;
        Sorter<Car> sorter = new PowerSublistCarSorter(new BubbleSorter<>(), Filter.EVEN);
        try {
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = false;
        }
        Assert.assertThat(result
                && (cars.get(0).getPower() == 98 && cars.get(1) == null && cars.get(2).getPower() == 108)
                ,"(SublistNegative): обработка списка с null элементом");
    }

    private static void nullFilterBehavior(){
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder()
                .setBrand("Chevrolet")
                .setModel("Cyan")
                .setYear(2010)
                .setColor("White")
                .setPower(108)
                .setPrice(1300000)
                .setIsNew(true)
                .build()
        );
        boolean result = false;
        try {
            Sorter<Car> sorter = new PowerSublistCarSorter(new BubbleSorter<>(), null);
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result,"(SublistNegative): обработка null фильтра");
    }

    private static void nullSorterBehavior(){
        List<Car> cars = new CustomList<>();
        cars.add(new CarBuilder()
                .setBrand("Chevrolet")
                .setModel("Cyan")
                .setYear(2010)
                .setColor("White")
                .setPower(108)
                .setPrice(1300000)
                .setIsNew(true)
                .build()
        );
        boolean result = false;
        try {
            Sorter<Car> sorter = new PowerSublistCarSorter(null,Filter.ODEN);
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result,"(SublistNegative): обработка null сортировщика");
    }
}
