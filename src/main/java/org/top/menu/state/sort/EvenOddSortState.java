package org.top.menu.state.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.top.comparator.CarComparator;
import org.top.config.GlobalSortConfig;
import org.top.data.CarDataService;
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
    private final CarDataService dataService;
    private final InputValidator validator;
    private final GlobalSortConfig sortConfig;

    public EvenOddSortState(CarDataService dataService, InputValidator validator, GlobalSortConfig sortConfig) {
        this.dataService = Objects.requireNonNull(dataService);
        this.validator = Objects.requireNonNull(validator);
        this.sortConfig = Objects.requireNonNull(sortConfig);
    }

    @Override
    public Result<Boolean> handle() {
        List<Car> currentList = dataService.getCollection();
        if (currentList.isEmpty()) {
            return Result.failure("Коллекция пуста!");
        }
        
        System.out.println(AnsiColor.CYAN.colorize("\n--- [Доп. 1] Сортировка элементов ---"));

        System.out.println("Выберите режим фильтрации:");
        System.out.println("1. Сортировать ЧЕТНЫЕ (нечетные на местах)");
        System.out.println("2. Сортировать НЕЧЕТНЫЕ (четные на местах)");
        System.out.print("Ваш выбор (1-2): ");
        Result<Integer> filterChoice = validator.validateMenuChoice(validator.readInt(), 1, 2);

        if (filterChoice.isFailure()) {
            return Result.failure(filterChoice.errorMessage());
        }

        AbstractSublistCarSorter.Filter filter = switch (filterChoice.value()) {
            case 1 -> AbstractSublistCarSorter.Filter.EVEN;
            default -> AbstractSublistCarSorter.Filter.ODEN;
        };

        System.out.println("3. Год производства");
        System.out.println("4. Мощность");
        System.out.print("Выберите числовое поле (3 или 4): ");
        
        Result<Integer> fieldResult = validator.validateMenuChoice(validator.readInt(), 3, 4);
        if (fieldResult.isFailure()) {
            return Result.failure(fieldResult.errorMessage());
        }

        System.out.print("Направление для этого поля (1 - По возрастанию, 2 - По убыванию): ");
        Result<Integer> directionResult = validator.validateDirection(validator.readInt());

        if (directionResult.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + directionResult.errorMessage()));
        }

        Sorter.Order order = switch (directionResult.value()) {
            case 2 -> Sorter.Order.REVERSE;
            default -> Sorter.Order.DIRECT;
        };

        Sorter<Car> baseSorter = sortConfig.getSorter();
        
        int choice = fieldResult.value();
        AbstractSublistCarSorter sublistSorter = switch (choice) {
            case 3 -> new YearSublistCarSorter(baseSorter, filter);
            default -> new PowerSublistCarSorter(baseSorter, filter);
        };

        Comparator<Car> targetComparator = switch (choice) {
            case 3 -> CarComparator.BY_YEAR;
            default -> CarComparator.BY_POWER;
        };

        sublistSorter.sort(currentList, targetComparator, order);

        System.out.println(AnsiColor.GREEN.colorize("[Успех]: Элементы поля успешно отсортированы!"));
        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "[Доп. 1] Сортировка четных или нечетных элементов";
    }
}
