package org.top.data;

import org.top.collection.CustomList;
import org.top.model.Car;
import org.top.util.Assert;

public final class CarDataManagerTest {
    public static void run() {
        testDataStorage();
    }

    private static void testDataStorage() {
        CustomList<Car> sharedList = new CustomList<>();
        CarDataManager manager = new CarDataManager(sharedList);
        
        Car car = new Car("Audi", "A4", 2021, "White", 190, 35000.0, false);
        manager.getCollection().add(car);

        Assert.assertThat(sharedList.size() == 1, "CarDataManager: Синхронизация данных через общую ссылку на CustomList");
        Assert.assertThat(manager.getCollection().get(0).getBrand().equals("Audi"), "CarDataManager: Корректный возврат данных из коллекции");
    }
}
