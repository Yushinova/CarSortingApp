package org.top.menu.state;

import org.top.menu.service.SortService;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;
import org.top.menu.util.Result;

public final class EvenOddSortState implements MenuState {
    private final SortService sortService;
    private final InputValidator validator;

    public EvenOddSortState(SortService sortService, InputValidator validator) {
        this.sortService = sortService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.println(AnsiColor.CYAN.colorize("\n--- Сортировка четных элементов ---"));
        System.out.println("3. Год производства");
        System.out.println("4. Мощность");
        System.out.print("Выберите числовое поле (3 или 4): ");
        
        Result<Integer> res = validator.validateMenuChoice(validator.readInt(), 3, 4);
        if (res.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: Поле должно быть 3 или 4."));
            return true;
        }
        
        sortService.executeEvenOddSort(res.value());
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 1] Сортировка только четных элементов";
    }
}
