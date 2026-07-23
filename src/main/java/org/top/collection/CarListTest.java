package org.top.collection;

import java.util.ArrayList;
import java.util.List;

import org.top.builder.CarBuilder;
import org.top.model.Car;

public class CarListTest {
    private CarList list;
    private Car tesla;
    private Car bmw;
    private Car audi;
    private Car lada;

    public void test() {
        list = new CarList();

        tesla = new CarBuilder()
                .setBrand("Tesla")
                .setModel("Model S")
                .setColor("Blue")
                .setYear(2020)
                .setPower(400)
                .build();
        bmw = new CarBuilder()
                .setBrand("BMW")
                .setColor("White")
                .setModel("M5")
                .setPower(250)
                .setPrice(100000)
                .setYear(2024)
                .build();
        audi = new CarBuilder()
                .setIsNew(true)
                .setBrand("Audi")
                .setModel("RS6")
                .setColor("Red")
                .setPower(300)
                .setPrice(200000)
                .setYear(2026)
                .build();
        lada = new CarBuilder()
                .setIsNew(true)
                .setBrand("Lada")
                .setModel("Vesta")
                .setColor("Orange")
                .setPower(122)
                .setPrice(14000)
                .setYear(2026)
                .setIsNew(true)
                .build();

        System.out.println("\n--- 1. Создание списка ---");
        System.out.println("Список пустой? (true): " + list.isEmpty());
        System.out.println("Размер списка (0): " + list.size());

        System.out.println("\n--- 2. Добавление элементов ---");
        list.add(tesla);
        list.add(audi);
        System.out.println("Добавили 2 машины. Размер теперь: " + list.size());
        System.out.println("Индекс 0 (Tesla): " + list.get(0));

        list.add(1, bmw); // Вставляем BMW между Tesla и Audi
        System.out.println("Вставили BMW на индекс 1. Теперь индекс 2 (Audi): " + list.get(2));
        System.out.println("Список пустой? (false): " + list.isEmpty());
        System.out.println("Размер списка (3): " + list.size());

        // 3. Поиск
        System.out.println("\n--- 3. Проверка поиска ---");
        System.out.println("Есть ли в списке Tesla? (Ожидаем true): " + list.contains(tesla));
        System.out.println("Есть ли в списке Lada? (Ожидаем false): " + list.contains(lada));
        System.out.println("Индекс машины BMW (Ожидаем 1): " + list.indexOf(bmw));

        // 4. Удаление
        System.out.println("\n--- 4. Удаление элементов ---");
        Car removed = list.remove(1); // Удаляем BMW
        System.out.println("Удалили по индексу 1. Вернулась машина (Ожидаем BMW): " + removed);
        System.out.println("Размер списка после удаления (Ожидаем 2): " + list.size());

        // 5. Тест метода toArray (наш любимый!)
        System.out.println("\n--- 5. Выгрузка в массив (toArray) ---");
        Car[] biggerArray = new Car[5]; // Массив с запасом
        list.toArray(biggerArray);
        System.out.println("Элемент [0] в массиве (Ожидаем Tesla): " + biggerArray[0]);
        System.out.println("Элемент [1] в массиве (Ожидаем Audi): " + biggerArray[1]);
        System.out.println("Элемент [2] в массиве (Ожидаем null — маркер конца): " + biggerArray[2]);

        // 6. Работа с коллекциями (addAll)
        System.out.println("\n--- 6. Добавление другой коллекции ---");
        List<Car> temporaryList = new ArrayList<>();
        temporaryList.add(lada);
        list.addAll(temporaryList);
        System.out.println(
                "Добавили Ладу из другой коллекции. Последний элемент (Ожидаем Lada): " + list.get(list.size() - 1));

        // 7. Обход через Итератор (цикл for-each)
        System.out.println("\n--- 7. Тест итератора (Финальный обход) ---");
        System.out.println("Выводим все оставшиеся машины в списке:");
        for (Car car : list) {
            System.out.println(" -> " + car);
        }

        System.out.println("\n=== ТЕСТ-ДРАЙВ ЗАВЕРШЕН ===");
    }

}
