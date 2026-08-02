package org.top.menu.state.fill;

import java.util.LinkedHashMap;
import java.util.Map;

import org.top.data.CarDataManager;
import org.top.io.CarDataConverter;
import org.top.io.ManualFill;
import org.top.io.RandomFill;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.state.MenuState;

public final class FillCollectionState implements MenuState {
    private final InputValidator validator;
    private final Map<Integer, MenuState> fillingActions = new LinkedHashMap<>();

    public FillCollectionState(CarDataManager dataManager, InputValidator validator, ManualFill manualFill, RandomFill randomFill, CarDataConverter carDataConverter) {
        this.validator = validator;
        fillingActions.put(1, new ManualFillingAction(dataManager, validator, manualFill));
        fillingActions.put(2, new RandomFillingAction(dataManager, validator, randomFill));
        fillingActions.put(3, new FileFillingAction(dataManager, validator, carDataConverter));
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
