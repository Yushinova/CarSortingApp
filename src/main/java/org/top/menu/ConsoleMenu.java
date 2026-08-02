package org.top.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.top.config.GlobalSortConfig;
import org.top.data.CarDataService;
import org.top.io.CarDataConverter;
import org.top.io.ManualFill;
import org.top.io.RandomFill;
import org.top.menu.common.InputValidator;
import org.top.menu.common.MenuEngine;
import org.top.menu.state.ExitState;
import org.top.menu.state.MenuState;
import org.top.menu.state.PrintCollectionState;
import org.top.menu.state.SaveFileState;
import org.top.menu.state.ThreadCountState;
import org.top.menu.state.fill.FillCollectionState;
import org.top.menu.state.sort.AlgorithmSelectState;
import org.top.menu.state.sort.CombinedSortAction;
import org.top.menu.state.sort.EvenOddSortState;

public final class ConsoleMenu {
    private final Map<Integer, MenuState> actions = new LinkedHashMap<>();
    private final MenuEngine engine;

    public ConsoleMenu(CarDataService dataService, InputValidator validator, ManualFill manual, RandomFill random, CarDataConverter converter, GlobalSortConfig sortConfig) {
        Objects.requireNonNull(dataService);
        Objects.requireNonNull(manual);
        Objects.requireNonNull(random);
        Objects.requireNonNull(converter);
        Objects.requireNonNull(sortConfig);

        this.engine = new MenuEngine(validator);

        actions.put(1, new FillCollectionState(dataService, validator, manual, random, converter, engine));
        actions.put(2, new PrintCollectionState(dataService, validator));
        actions.put(3, new AlgorithmSelectState(validator, sortConfig));
        actions.put(4, new CombinedSortAction(dataService, validator, sortConfig));
        actions.put(5, new EvenOddSortState(dataService, validator, sortConfig));
        actions.put(6, new ThreadCountState(dataService, validator)); 
        actions.put(7, new SaveFileState(dataService, validator, converter)); 
        actions.put(0, new ExitState());
    }

    public void run() {
        engine.run("ГЛАВНОЕ МЕНЮ", actions);
    }
}
