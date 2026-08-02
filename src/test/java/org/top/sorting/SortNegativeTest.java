package org.top.sorting;

import org.top.builder.CarBuilder;
import org.top.collection.CustomList;
import org.top.comparator.CarComparator;
import org.top.model.Car;
import org.top.strategy.Sorter;
import org.top.util.Assert;

import java.util.List;

public final class SortNegativeTest {
    public static void run(){
        nullListBehavior();
        emptyListBehavior();
        oneElementBehavior();
        nullElementBehavior();
        nullComparatorBehavior();
        nullOrderBehavior();
    }

    private static void nullListBehavior(){
        List<Car> cars = null;
        boolean result = false;
        Sorter<Car> sorter = new BubbleSorter<>();
        try {
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result, "(SortNegative): обработка null списка");
    }

    private static void emptyListBehavior(){
        List<Car> cars = new CustomList<>();
        boolean result = false;
        Sorter<Car> sorter = new BubbleSorter<>();
        try {
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result && cars.isEmpty(), "(SortNegative): обработка пустого списка");
    }

    private static void oneElementBehavior(){
        List<Car> cars = new CustomList<>();
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
        List<Car> copy = new CustomList<>();
        copy.addAll(cars);
        Sorter<Car> sorter = new BubbleSorter<>();
        sorter.sort(cars,CarComparator.BY_POWER);
        Assert.assertThat(cars.get(0) == copy.get(0),"(SortNegative): обработка списка с одним элементом");
    }

    private static void nullElementBehavior(){
        List<Car> cars = new CustomList<>();
        cars.add(null);
        boolean result = true;
        Sorter<Car> sorter = new BubbleSorter<>();
        try {
            sorter.sort(cars, CarComparator.BY_POWER);
        } catch (Exception e) {
            result = false;
        }
        Assert.assertThat(result && (cars.size() == 1),"(SortNegative): обработка списка с null элементом");
    }

    private static void nullComparatorBehavior(){
        List<Car> cars = new CustomList<>();
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
        boolean result = false;
        Sorter<Car> sorter = new BubbleSorter<>();
        try {
            sorter.sort(cars, null);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result,"(SortNegative): обработка null компаратора");
    }

    private static void nullOrderBehavior(){
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
        boolean result = false;
        Sorter<Car> sorter = new BubbleSorter<>();
        try {
            sorter.sort(cars, CarComparator.BY_POWER, null);
        } catch (Exception e) {
            result = true;
        }
        Assert.assertThat(result,"(SortNegative): обработка null порядка сортировки");
    }
}
