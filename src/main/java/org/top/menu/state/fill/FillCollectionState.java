package org.top.menu.state.fill;

import java.util.LinkedHashMap;
import java.util.Map;

import org.top.menu.service.CarDataService;
import org.top.menu.state.MenuState;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;

public final class FillCollectionState implements MenuState {
    private final InputValidator validator;
    private final Map<Integer, MenuState> fillingActions = new LinkedHashMap<>();

    public FillCollectionState(CarDataService dataService, InputValidator validator) {
        this.validator = validator;
        fillingActions.put(1, new ManualFillingAction(dataService, validator));
        fillingActions.put(2, new RandomFillingAction(dataService, validator));
        fillingActions.put(3, new FileFillingAction(dataService, validator));
    }

    @Override
    public boolean handle() {
        printSubMenu();
        int rawChoice = validator.readInt();
        
        if (!fillingActions.containsKey(rawChoice)) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: Неверный способ заполнения."));
            return true;
        }

        fillingActions.get(rawChoice).handle();
        return true;
    }

    @Override
    public String getDescription() {
        return "Заполнить коллекцию автомобилей";
    }

    private void printSubMenu() {
        System.out.println(AnsiColor.CYAN.colorize("\n--- Параметры заполнения ---"));
        fillingActions.forEach((key, action) -> 
            System.out.println(AnsiColor.YELLOW.colorize(key + ".") + " " + action.getDescription())
        );
        System.out.print(AnsiColor.CYAN.colorize("Выберите действие: "));
    }
}
