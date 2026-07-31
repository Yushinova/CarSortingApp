package org.top.menu.state.fill;

import java.util.List;

import org.top.collection.CustomList;
import org.top.data.CarDataManager;
import org.top.io.RandomFill;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;

public final class RandomFillingAction implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;
    private final RandomFill randomFill;

    public RandomFillingAction(CarDataManager dataManager, InputValidator validator, RandomFill randomFill) {
        this.dataManager = dataManager;
        this.validator = validator;
        this.randomFill = randomFill;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean handle() {
        System.out.print("Введите длину коллекции для генерации: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());
        
        if (sizeResult.isFailure()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + sizeResult.errorMessage()));
            return true;
        }
        
        CustomList<Car> tempContainer = new CustomList<>();
        CustomList<Car> filledContainer = (CustomList<Car>) randomFill.fill(tempContainer, sizeResult.value());
        
        List<Car> currentList = dataManager.getCollection();
        currentList.addAll(filledContainer);
        dataManager.setCollection(currentList);
        
        return true;
    }

    @Override
    public String getDescription() {
        return "Сгенерировать случайным образом (Рандом)";
    }
}
