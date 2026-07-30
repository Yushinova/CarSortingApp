package org.top.menu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.top.menu.service.CarDataService;
import org.top.menu.service.CarPrintService;
import org.top.menu.service.FileExportService;
import org.top.menu.service.SortService;
import org.top.menu.state.EvenOddSortState;
import org.top.menu.state.ExitState;
import org.top.menu.state.MenuState;
import org.top.menu.state.PrintCollectionState;
import org.top.menu.state.SaveFileState;
import org.top.menu.state.ThreadCountState;
import org.top.menu.state.fill.FillCollectionState;
import org.top.menu.state.sort.CombinedSortAction;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;

public final class ConsoleMenu {
    private final InputValidator validator;
    private final Map<Integer, MenuState> actions = new LinkedHashMap<>();

    public ConsoleMenu(CarDataService data, CarPrintService print, SortService sort, FileExportService export, InputValidator validator) {
        this.validator = validator;

        actions.put(1, new FillCollectionState(data, validator));
        actions.put(2, new PrintCollectionState(print));
        actions.put(3, new CombinedSortAction(sort, validator));
        actions.put(4, new EvenOddSortState(sort, validator));
        actions.put(5, new SaveFileState(export, validator));
        actions.put(6, new ThreadCountState(validator));
        actions.put(0, new ExitState());
    }

    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            printMainMenu();
            int rawChoice = validator.readInt();
            
            if (!actions.containsKey(rawChoice)) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: Такого пункта меню не существует."));
                continue;
            }

            MenuState selectedState = actions.get(rawChoice);
            keepRunning = selectedState.handle();
        }
    }

    private void printMainMenu() {
        System.out.println(AnsiColor.CYAN.colorize("\n========================================"));
        System.out.println(AnsiColor.CYAN.colorize("          ГЛАВНОЕ МЕНЮ ПРИЛОЖЕНИЯ       "));
        System.out.println(AnsiColor.CYAN.colorize("========================================"));
        
        actions.forEach((key, state) -> {
            if (key != 0) {
                System.out.println(AnsiColor.YELLOW.colorize(key + ".") + " " + state.getDescription());
            }
        });
        
        if (actions.containsKey(0)) {
            System.out.println(AnsiColor.RED.colorize("0.") + " " + actions.get(0).getDescription());
        }
        System.out.print(AnsiColor.CYAN.colorize("Выберите действие: "));
    }
}
