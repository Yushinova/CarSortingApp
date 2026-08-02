package org.top.menu.state.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.top.comparator.CarComparator;
import org.top.config.GlobalSortConfig;
import org.top.data.CarDataService;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;
import org.top.strategy.FieldSortOrder;
import org.top.strategy.Sorter;

public final class CombinedSortAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;

    public CombinedSortAction(CarDataService dataService, InputValidator validator) {
        this.dataService = dataService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        List<Car> currentList = dataService.getCollection();
        if (currentList == null || currentList.isEmpty()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: Коллекция пуста. Сначала заполните её!"));
            return true;
        }

        List<FieldSortOrder> sortOrders = new ArrayList<>();

        while (true) {
            System.out.println(AnsiColor.PURPLE.colorize("\n--- Выбор поля для сортировки ---"));
            System.out.println("Поля: 1-Бренд, 2-Модель, 3-Год, 4-Мощность, 5-Цена, 6-Цвет, 7-Состояние(Новая/БУ)");
            System.out.print("Выберите номер поля (1-7): ");
            
            Result<Integer> fieldResult = validator.validateMenuChoice(validator.readInt(), 1, 7);
            if (fieldResult.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + fieldResult.errorMessage()));
                continue;
            }

            int selectedField = fieldResult.value();

            Sorter.Order fieldOrder;
            while (true) {
                System.out.print("Направление для этого поля (1 - По возрастанию, 2 - По убыванию): ");
                Result<Integer> directionResult = validator.validateDirection(validator.readInt());
                if (directionResult.isFailure()) {
                    System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + directionResult.errorMessage()));
                    continue;
                }
                fieldOrder = switch (directionResult.value()) {
                    case 2 -> Sorter.Order.REVERSE;
                    default -> Sorter.Order.DIRECT;
                };
                break;
            }

            int existingIndex = -1;
            for (int i = 0; i < sortOrders.size(); i++) {
                if (sortOrders.get(i).fieldId() == selectedField) {
                    existingIndex = i;
                    break;
                }
            }

            if (existingIndex != -1) {
                System.out.println(AnsiColor.YELLOW.colorize("[Инфо]: Поле уже было в цепочке. Обновляем его направление."));
                sortOrders.set(existingIndex, new FieldSortOrder(selectedField, fieldOrder));
            } else {
                sortOrders.add(new FieldSortOrder(selectedField, fieldOrder));
            }

            boolean shouldBreak = false;
            while (true) {
                System.out.print("Добавить ли еще поле в цепочку сортировки? (1 - Да, 2 - Нет): ");
                Result<Integer> confirmationResult = validator.validateConfirmation(validator.readInt());
                if (confirmationResult.isFailure()) {
                    System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + confirmationResult.errorMessage()));
                    continue;
                }
                if (confirmationResult.value() == 2) {
                    shouldBreak = true;
                }
                break;
            }

            if (shouldBreak) {
                break;
            }
        }

        Comparator<Car> finalComparator = buildChainComparator(sortOrders);
        Sorter<Car> activeSorter = GlobalSortConfig.getInstance().getSorter();
        
        List<Car> sortedList = activeSorter.sort(currentList, finalComparator, Sorter.Order.DIRECT);
        dataService.setCollection(sortedList);

        System.out.println(AnsiColor.GREEN.colorize("[Успех]: Коллекция успешно отсортирована кастомной цепочкой полей!"));
        return true;
    }

    private Comparator<Car> buildChainComparator(List<FieldSortOrder> orders) {
        Comparator<Car> chain = prepareComparator(orders.get(0));

        for (int i = 1; i < orders.size(); i++) {
            chain = chain.thenComparing(prepareComparator(orders.get(i)));
        }
        return chain;
    }

    private Comparator<Car> prepareComparator(FieldSortOrder order) {
        Comparator<Car> base = getComparatorById(order.fieldId());
        return switch (order.order()) {
            case REVERSE -> base.reversed();
            default -> base;
        };
    }

    private Comparator<Car> getComparatorById(int id) {
        return switch (id) {
            case 1 -> CarComparator.BY_BRAND;
            case 2 -> CarComparator.BY_MODEL;
            case 3 -> CarComparator.BY_YEAR;
            case 4 -> CarComparator.BY_POWER;
            case 5 -> CarComparator.BY_PRICE;
            case 6 -> CarComparator.BY_COLOR;
            case 7 -> CarComparator.BY_IS_NEW;
            default -> CarComparator.BY_POWER;
        };
    }

    @Override
    public String getDescription() {
        return "Базовая сортировка данных (Паттерн Стратегия с динамической цепочкой)";
    }
}
