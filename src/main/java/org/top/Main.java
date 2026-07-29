package org.top;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.top.menu.ConsoleMenu;
import org.top.menu.service.CarDataService;
import org.top.menu.service.CarPrintService;
import org.top.menu.service.FileExportService;
import org.top.menu.service.SortService;
import org.top.menu.util.InputValidator;
import org.top.menu.util.SortOrder;

final class StubCarDataService implements CarDataService {
    @Override public void fillManually(int size) {}
    @Override public void fillRandomly(int size) {}
    @Override public void fillFromFile(String path) throws IOException {}
}

final class StubCarPrintService implements CarPrintService {
    @Override public void printCollection() { System.out.println("Печать коллекции..."); }
}

final class StubSortService implements SortService {
    @Override public void executeSort(List<SortOrder> sortOrders) {}
    @Override public void executeEvenOddSort(int fieldId) {}
}

final class StubFileExportService implements FileExportService {
    @Override public void saveLastResultToFile(String path) throws IOException {}
}

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public final class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner);

        CarDataService dataService = new StubCarDataService();
        CarPrintService printService = new StubCarPrintService();
        SortService sortService = new StubSortService();
        FileExportService exportService = new StubFileExportService();

        ConsoleMenu menu = new ConsoleMenu(dataService, printService, sortService, exportService, validator);
        menu.run();

        // List<Car> cars = new ArrayList<>();
        // cars.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000, false));
        // cars.add(new Car("BMW", "X5", 2022, "Black", 250, 45000, true));
        // cars.add(new Car("Audi", "A4", 2021, "White", 190, 35000, false));
        // cars.add(new Car("Honda", "Civic", 2019, "Blue", 180, 25000, true));
        // cars.add(new Car("Toyota", "Corolla", 2021, "Silver", 140, 22000, true));
        // cars.add(new Car("BMW", "X3", 2021, "Black", 180, 40000, false));
        // cars.add(new Car("Mercedes", "E-Class", 2018, "White", 220, 50000, false));
        // cars.add(new Car("Audi", "Q7", 2022, "Black", 280, 55000, true));

        // System.out.println("___Исходный список___");
        // cars.forEach(System.out::println);

        // System.out.println("___Отсортированный список по мощности___");
        // cars.stream()
        //         .sorted(CarComparator.BY_POWER)
        //         .forEach(System.out::println);

        // System.out.println("___Отсортированный список старых по году по убыванию___");
        // cars.stream()
        //         .filter(car -> !car.isNew())
        //         .sorted(CarComparator.BY_YEAR.reversed())//можно прям в стриме вызывать, если нужен другой порядок
        //         .forEach(System.out::println);

        // System.out.println("___Отсортированный список новых по бренду/цене___");
        // cars.stream()
        //         .filter(Car::isNew)
        //         .sorted(CarComparator.BY_BRAND.thenComparing(CarComparator.BY_PRICE))
        //         .forEach(System.out::println);
    }
}
