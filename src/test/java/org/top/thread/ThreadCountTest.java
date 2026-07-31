package org.top.thread;

import java.util.List;

import org.top.collection.CustomList;
import org.top.model.Car;
import org.top.util.Assert;

public final class ThreadCountTest {
    public static void run() {
        testMultiThreadedCounterWithCustomList();
    }

    private static void testMultiThreadedCounterWithCustomList() {
        List<Car> cars = new CustomList<>();
        
        Car targetCar1 = new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false);
        Car targetCar2 = new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false);
        Car differentCar = new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true);

        cars.add(targetCar1);
        cars.add(differentCar);
        cars.add(targetCar2);

        int totalCount = ThreadManager.multiThreadCounter(cars, targetCar1, 2);

        Assert.assertThat(totalCount == 2, "Фича 17 (Доп. 4): Интеграционный тест ThreadManager группы на CustomList");
    }
}
