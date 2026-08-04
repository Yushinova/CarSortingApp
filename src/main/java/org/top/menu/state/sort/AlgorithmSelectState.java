package org.top.menu.state.sort;

import java.util.Objects;

import org.top.config.GlobalSortConfig;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;
import org.top.sorting.BubbleSorter;
import org.top.sorting.MergeSorter;
import org.top.sorting.ShuttleSorter;
import org.top.strategy.Sorter;

public final class AlgorithmSelectState implements MenuState {
    private final InputValidator validator;
    private final GlobalSortConfig config;

    public AlgorithmSelectState(InputValidator validator, GlobalSortConfig config) {
        this.validator = Objects.requireNonNull(validator);
        this.config = Objects.requireNonNull(config);
    }

    @Override
    public Result<Boolean> handle() {
        System.out.println(AnsiColor.PURPLE.colorize("\n--- ВЫБОР АЛГОРИТМА СОРТИРОВКИ ---"));
        System.out.println("1. Пузырьковая сортировка");
        System.out.println("2. Сортировка слиянием");
        System.out.println("3. Челночная сортировка");
        System.out.print("Выберите номер (1-3): ");

        Result<Integer> res = validator.validateMenuChoice(validator.readInt(), 1, 3);

        if (res.isFailure()) {
            return Result.failure(res.errorMessage());
        }

        Sorter<Car> selectedSorter = switch (res.value()) {
            case 1 -> new BubbleSorter<>();
            case 2 -> new MergeSorter<>();
            case 3 -> new ShuttleSorter<>();
            default -> throw new IllegalStateException("Выбран неизвестный алгоритм: " + res.value());
        };

        config.setSorter(selectedSorter);

        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "Выбрать алгоритм сортировки";
    }
}
