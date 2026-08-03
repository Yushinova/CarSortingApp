package org.top.menu.state;

import java.util.List;
import java.util.Objects;

import org.top.data.CarDataService;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.model.Car;

public final class PrintCollectionState implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;

    public PrintCollectionState(CarDataService dataService, InputValidator validator) {
        this.dataService = Objects.requireNonNull(dataService);
        this.validator = Objects.requireNonNull(validator);
    }

    @Override
    public Result<Boolean> handle() {
        List<Car> list = dataService.getCollection();
        if (list.isEmpty()) {
            System.out.println(AnsiColor.RED.colorize("[Информация]: Коллекция пуста."));
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(AnsiColor.YELLOW.colorize((i + 1) + ".") + " " + list.get(i));
            }
        }
        
        System.out.println(AnsiColor.CYAN.colorize("\nНажмите любую клавишу, чтобы продолжить..."));
        validator.readString();
        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "Вывести текущую коллекцию";
    }
}
