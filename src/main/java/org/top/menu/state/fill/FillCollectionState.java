package org.top.menu.state.fill;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.top.data.CarDataService;
import org.top.io.CarDataConverter;
import org.top.io.ManualFill;
import org.top.io.RandomFill;
import org.top.menu.common.InputValidator;
import org.top.menu.common.MenuEngine;
import org.top.menu.common.Result;
import org.top.menu.state.BackState;
import org.top.menu.state.MenuState;

public final class FillCollectionState implements MenuState {
    private final Map<Integer, MenuState> actions = new LinkedHashMap<>();
    private final MenuEngine engine;

    public FillCollectionState(CarDataService dataService, InputValidator validator, ManualFill manual, RandomFill random, CarDataConverter converter, MenuEngine engine) {
        Objects.requireNonNull(dataService);
        Objects.requireNonNull(manual);
        Objects.requireNonNull(random);
        Objects.requireNonNull(converter);

        this.engine = engine;

        actions.put(1, new ManualFillingAction(dataService, validator, manual));
        actions.put(2, new RandomFillingAction(dataService, validator, random));
        actions.put(3, new FileFillingAction(dataService, validator, converter));
        actions.put(0, new BackState());
    }

    @Override
    public Result<Boolean> handle() {
        engine.run("МЕНЮ ЗАПОЛНЕНИЯ", actions);
        return Result.success(true); 
    }

    @Override
    public String getDescription() {
        return "Заполнить коллекцию данными";
    }
}
