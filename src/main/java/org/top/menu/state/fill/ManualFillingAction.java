package org.top.menu.state.fill;

import java.util.List;

import org.top.collection.CustomList;
import org.top.data.CarDataManager;
import org.top.io.ManualFill;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;

public final class ManualFillingAction implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;
    private final ManualFill manualFill;

    public ManualFillingAction(CarDataManager dataManager, InputValidator validator, ManualFill manualFill) {
        this.dataManager = dataManager;
        this.validator = validator;
        this.manualFill = manualFill;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean handle() {
        System.out.print("Введите длину коллекции для ручного ввода: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());
        
        if (sizeResult.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + sizeResult.errorMessage()));
            return true;
        }
        
        CustomList<Car> tempContainer = new CustomList<>();
        CustomList<Car> filledContainer = (CustomList<Car>) manualFill.fill(tempContainer, sizeResult.value());
        
        List<Car> currentList = dataManager.getCollection();
        currentList.addAll(filledContainer);
        dataManager.setCollection(currentList);
        
        return true;
    }

    @Override
    public String getDescription() {
        return "Заполнить вручную с консоли";
    }
}
