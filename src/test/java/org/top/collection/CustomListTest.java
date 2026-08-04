package org.top.collection;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.util.Assert;

public final class CustomListTest {
    public static void run() {
        testAddAndResizeAndRemove();
        testGetElement();
        testContains();
        testIterator();
        testToArray();
    }

    private static void testToArray() {
        CustomList<Car> list = new CustomList<>();

        Car tesla = new CarBuilder()
                .setBrand("Tesla")
                .setModel("Model S")
                .setColor("Blue")
                .setYear(2020)
                .setPower(400)
                .build();
        Car bmw = new CarBuilder()
                .setBrand("BMW")
                .setColor("White")
                .setModel("M5")
                .setPower(250)
                .setPrice(100000)
                .setYear(2024)
                .build();
        Car audi = new CarBuilder()
                .setIsNew(true)
                .setBrand("Audi")
                .setModel("RS6")
                .setColor("Red")
                .setPower(300)
                .setPrice(200000)
                .setYear(2026)
                .build();
        Car lada = new CarBuilder()
                .setIsNew(true)
                .setBrand("Lada")
                .setModel("Vesta")
                .setColor("Orange")
                .setPower(122)
                .setPrice(14000)
                .setYear(2026)
                .setIsNew(true)
                .build();

        list.add(tesla);
        list.add(bmw);
        list.add(audi);
        list.add(lada);
        Car[] array = new Car[5];
        list.toArray(array);
        Assert.assertThat(array[0] == tesla, "CustomList: Проверка конвертации в массив");
        Assert.assertThat(array[1] == bmw, "CustomList: Проверка конвертации в массив");
        Assert.assertThat(array[3] == lada, "CustomList: Проверка конвертации в массив");
    }

    private static void testIterator() {
        CustomList<Car> list = new CustomList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false));
        }
        System.out.println("Выводим все оставшиеся машины в списке:");
        System.out.println("Выводим все машины в списке " + list.size() + ":");
        int count = 0;
        for (Car car : list) {
            count++;
            System.out.println(count + ". " + car);
        }
    }

    private static void testContains() {
        CustomList<Car> list = new CustomList<>();
        Car testCar = new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true);
        list.add(testCar);
        Assert.assertThat(list.contains(testCar), "CustomList: Проверка поиска и наличия объекта");
        list.remove(testCar);
        Assert.assertThat(!list.contains(testCar), "CustomList: Проверка поиска и наличия объекта");
    }

    private static void testAddAndResizeAndRemove() {
        CustomList<Car> list = new CustomList<>();

        Assert.assertThat(list.isEmpty(), "CustomList: Список создается пустой");
        for (int i = 0; i < 15; i++) {
            list.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false));
        }
        Assert.assertThat(list.size() == 15, "CustomList: Проверка динамического расширения массива (resize)");
        Assert.assertThat(!list.isEmpty(), "CustomList: Проверка на пустоту массива");
        Car car1 = list.remove(10);
        Car car2 = list.remove(10);
        Assert.assertThat(car1 != car2, "CustomList: Проверка удаления объектов");
        Assert.assertThat(list.size() == 13, "CustomList: Проверка изменения размерности");
    }

    private static void testGetElement() {
        CustomList<Car> list = new CustomList<>();
        Car testCar = new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true);
        list.add(testCar);
        Assert.assertThat(list.get(0).equals(testCar), "CustomList: Получение элемента по индексу");
    }
}
