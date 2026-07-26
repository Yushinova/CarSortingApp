package org.top.menu.state;

import org.top.menu.service.CarPrintService;

public final class PrintCollectionState implements MenuState {
    private final CarPrintService printService;

    public PrintCollectionState(CarPrintService printService) {
        this.printService = printService;
    }

    @Override
    public boolean handle() {
        printService.printCollection();
        return true;
    }

    @Override
    public String getDescription() {
        return "Вывести текущую коллекцию";
    }
}
