package org.top.menu.state.fill;

import org.top.menu.service.CarDataService;
import org.top.menu.state.MenuState;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;
import org.top.menu.util.Result;

public final class RandomFillingAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;

    public RandomFillingAction(CarDataService dataService, InputValidator validator) {
        this.dataService = dataService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.print("Введите длину коллекции для генерации: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());
        
        if (sizeResult.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + sizeResult.errorMessage()));
            return true;
        }
        
        dataService.fillRandomly(sizeResult.value());
        return true;
    }

    @Override
    public String getDescription() {
        return "Сгенерировать случайным образом (Рандом)";
    }
}
