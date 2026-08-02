package org.top.collection;

import org.top.model.Car;
import org.top.util.Assert;

public final class CustomListTest {
    public static void run() {
        testAddAndResize();
        testGetElement();
    }

    private static void testAddAndResize() {
        CustomList<Car> list = new CustomList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false));
        }
        Assert.assertThat(list.size() == 15, "CustomList: Проверка динамического расширения массива (resize)");
    }

    private static void testGetElement() {
        CustomList<Car> list = new CustomList<>();
        Car testCar = new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true);
        list.add(testCar);
        Assert.assertThat(list.get(0).equals(testCar), "CustomList: Получение элемента по индексу");
    }
}
