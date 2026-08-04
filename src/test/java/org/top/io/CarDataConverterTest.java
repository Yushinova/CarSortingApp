package org.top.io;

import org.top.model.Car;
import org.top.util.Assert;

public final class CarDataConverterTest {
    public static void run() {
        testFromValidString();
        testToStringConversion();
    }

    private static void testFromValidString() {
        CarDataConverter converter = new CarDataConverter();
        String rawData = "Toyota;Camry;2020;Red;200;30000.0;false";
        
        Car car = converter.fromString(rawData);
        
        boolean isValid = car != null 
                && "Toyota".equals(car.getBrand()) 
                && "Camry".equals(car.getModel()) 
                && car.getYear() == 2020;

        Assert.assertThat(isValid, "CarDataConverter: Корректный парсинг строки из cars.txt");
    }

    private static void testToStringConversion() {
        CarDataConverter converter = new CarDataConverter();
        Car car = new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true);
        
        String result = converter.toString(car);
        Assert.assertThat(result.contains("BMW;X5;2022;Black;250"), "CarDataConverter: Корректная сериализация объекта в строку с разделителями ';'");
    }
}
