package org.top.menu.state.fill;

import java.util.List;
import java.util.Objects;

import org.top.collection.CustomList;
import org.top.data.CarDataService;
import org.top.io.ManualFill;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;
import org.top.model.Car;

public final class ManualFillingAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;
    private final ManualFill manualFill;

    public ManualFillingAction(CarDataService dataService, InputValidator validator, ManualFill manualFill) {
        this.dataService = Objects.requireNonNull(dataService);
        this.validator = Objects.requireNonNull(validator);
        this.manualFill = Objects.requireNonNull(manualFill);
    }

    @Override
    public Result<Boolean> handle() {
        System.out.print("Введите длину коллекции для ручного ввода: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());
        
        if (sizeResult.isFailure()) {
            return Result.failure(sizeResult.errorMessage());
        }

        dataService.clearCollection();
        
        List<Car> tempContainer = new CustomList<>();
        List<Car> filledList = manualFill.fill(tempContainer, sizeResult.value());
        
        dataService.add(filledList.stream());
        
        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "Заполнить вручную с консоли";
    }
}
