package org.top.menu.state.fill;

import org.top.collection.CustomList;
import org.top.data.CarDataService;
import org.top.io.RandomFill;
import org.top.menu.state.MenuState;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.model.Car;
import java.util.List;
import java.util.Objects;

public final class RandomFillingAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;
    private final RandomFill randomFill;

    public RandomFillingAction(CarDataService dataService, InputValidator validator, RandomFill randomFill) {
        this.dataService = Objects.requireNonNull(dataService);
        this.validator = Objects.requireNonNull(validator);
        this.randomFill = Objects.requireNonNull(randomFill);
    }

    @Override
    public Result<Boolean> handle() {
        System.out.print("Введите длину коллекции для генерации: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());
        
        if (sizeResult.isFailure()) {
            return Result.failure(sizeResult.errorMessage());
        }

        dataService.clearCollection();
        
        List<Car> tempContainer = new CustomList<>();
        List<Car> list = randomFill.fill((CustomList) tempContainer, sizeResult.value());
        
        dataService.add(list.stream());
        
        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "Сгенерировать случайным образом";
    }
}
