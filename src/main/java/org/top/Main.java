package org.top;

import java.util.Scanner;

import org.top.collection.CustomList;
import org.top.data.CarDataManager;
import org.top.io.CarDataConverter;
import org.top.io.ManualFill;
import org.top.io.RandomFill;
import org.top.menu.ConsoleMenu;
import org.top.menu.common.InputValidator;
import org.top.model.Car;

public final class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner);

        CustomList<Car> sharedCustomList = new CustomList<>();
        CarDataManager dataManager = new CarDataManager(sharedCustomList);

        ManualFill manualFill = new ManualFill(scanner);
        RandomFill randomFill = new RandomFill();
        CarDataConverter converter = new CarDataConverter();

        ConsoleMenu menu = new ConsoleMenu(dataManager, validator, manualFill, randomFill, converter);
        menu.run();
    }
}
