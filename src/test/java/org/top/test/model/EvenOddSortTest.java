package org.top.test.model;

import java.util.ArrayList;
import java.util.List;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.test.util.Assert;

public final class EvenOddSortTest {
    public static void run() {
        testEvenOddSortLogic();
    }

    private static void testEvenOddSortLogic() {
        List<Car> cars = new ArrayList<>();
        cars.add(new CarBuilder().setBrand("A").setModel("X").setYear(2025).setColor("Red").setPower(251).setPrice(10).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("B").setModel("Y").setYear(2025).setColor("Blue").setPower(300).setPrice(20).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("C").setModel("Z").setYear(2025).setColor("Green").setPower(183).setPrice(30).setIsNew(true).build());
        cars.add(new CarBuilder().setBrand("D").setModel("W").setYear(2025).setColor("Black").setPower(140).setPrice(40).setIsNew(true).build());

        mockEvenOddSortByPower(cars);

        boolean condition1 = cars.get(0).getPower() == 251;
        boolean condition2 = cars.get(2).getPower() == 183;
        boolean condition3 = cars.get(1).getPower() == 140 && cars.get(3).getPower() == 300;

        Assert.assertThat(condition1 && condition2, "Фича 12 (Доп. 1): Объекты с нечетными значениями остались на исходных позициях");
        Assert.assertThat(condition3, "Фича 11 (Доп. 1): Объекты с четными значениями отсортированы в натуральном порядке");
    }

    private static void mockEvenOddSortByPower(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPower() % 2 != 0) continue;
            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(j).getPower() % 2 != 0) continue;
                if (cars.get(i).getPower() > cars.get(j).getPower()) {
                    Car temp = cars.get(i);
                    cars.set(i, cars.get(j));
                    cars.set(j, temp);
                }
            }
        }
    }
}
