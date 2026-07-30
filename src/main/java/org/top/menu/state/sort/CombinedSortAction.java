package org.top.menu.state.sort;

import java.util.ArrayList;
import java.util.List;

import org.top.menu.service.SortService;
import org.top.menu.state.MenuState;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;
import org.top.menu.util.Result;
import org.top.menu.util.SortOrder;

public final class CombinedSortAction implements MenuState {
    private final SortService sortService;
    private final InputValidator validator;

    public CombinedSortAction(SortService sortService, InputValidator validator) {
        this.sortService = sortService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.println(AnsiColor.PURPLE.colorize("\n[Конструктор цепочки] Выберите до 3 полей (0 - закончить выбор):"));
        List<SortOrder> sortOrders = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            System.out.println("Поля: 1-Бренд, 2-Модель, 3-Год, 4-Мощность, 5-Цена");
            System.out.print("Поле " + i + " (1-5): ");
            int rawField = validator.readInt();
            if (rawField == 0) break;

            Result<Integer> fieldResult = validator.validateMenuChoice(rawField, 1, 5);
            if (fieldResult.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + fieldResult.errorMessage()));
                i--;
                continue;
            }

            System.out.print("Направление для этого поля (1 - Возрастание [дефолт], 2 - Убывание): ");
            boolean ascending = validator.readInt() != 2;

            sortOrders.add(new SortOrder(fieldResult.value(), ascending));
        }

        if (sortOrders.isEmpty()) {
            System.out.println(AnsiColor.RED.colorize("[Отмена]: Вы не выбрали ни одного поля для сортировки."));
            return true;
        }

        sortService.executeSort(sortOrders);
        return true;
    }

    @Override
    public String getDescription() {
        return "Настраиваемая сортировка (По одному или нескольким полям)";
    }
}
