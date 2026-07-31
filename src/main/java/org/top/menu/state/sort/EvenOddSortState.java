package org.top.menu.state.sort;

import java.util.Comparator;
import java.util.List;

import org.top.comparator.CarComparator;
import org.top.config.GlobalSortConfig;
import org.top.data.CarDataManager;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;
import org.top.sorting.AbstractSublistCarSorter;
import org.top.sorting.PowerSublistCarSorter;
import org.top.sorting.YearSublistCarSorter;
import org.top.strategy.Sorter;

public final class EvenOddSortState implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;

    public EvenOddSortState(CarDataManager dataManager, InputValidator validator) {
        this.dataManager = dataManager;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        List<Car> currentList = dataManager.getCollection();
        if (currentList == null || currentList.isEmpty()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: Коллекция пуста!"));
            return true;
        }

        System.out.println(AnsiColor.CYAN.colorize("\n--- [Доп. 1] Сортировка только четных элементов ---"));
        System.out.println("3. Год производства");
        System.out.println("4. Мощность");
        System.out.print("Выберите числовое поле (3 или 4): ");
        
        Result<Integer> res = validator.validateMenuChoice(validator.readInt(), 3, 4);
        if (res.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + res.errorMessage()));
            return true;
        }

        int choice = res.value();

        Sorter.Order order;
        while (true) {
            System.out.print("Направление для этого поля (1 - По возрастанию, 2 - По убыванию): ");
            Result<Integer> directionResult = validator.validateDirection(validator.readInt());
            if (directionResult.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + directionResult.errorMessage()));
                continue;
            }
            order = switch (directionResult.value()) {
                case 2 -> Sorter.Order.REVERSE;
                default -> Sorter.Order.DIRECT;
            };
            break;
        }

        Sorter<Car> baseSorter = GlobalSortConfig.getInstance().getSorter();
        
        AbstractSublistCarSorter sublistSorter = switch (choice) {
            case 3 -> new YearSublistCarSorter(baseSorter, AbstractSublistCarSorter.Filter.EVEN);
            default -> new PowerSublistCarSorter(baseSorter, AbstractSublistCarSorter.Filter.EVEN);
        };

        Comparator<Car> targetComparator = switch (choice) {
            case 3 -> CarComparator.BY_YEAR;
            default -> CarComparator.BY_POWER;
        };

        List<Car> sortedList = sublistSorter.sort(currentList, targetComparator, order);
        dataManager.setCollection(sortedList);

        System.out.println(AnsiColor.GREEN.colorize("[Успех]: Четные элементы поля успешно отсортированы!"));
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 1] Сортировка только четных элементов (с выбором направления)";
    }
}
