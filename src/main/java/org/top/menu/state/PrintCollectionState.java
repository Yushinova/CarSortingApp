package org.top.menu.state;

import org.top.data.CarDataManager;
import org.top.menu.common.AnsiColor;
import org.top.model.Car;

public final class PrintCollectionState implements MenuState {
    private final CarDataManager dataManager;

    public PrintCollectionState(CarDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean handle() {
        if (dataManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
            return true;
        }
        
        for (int i = 0; i < dataManager.getCollection().size(); i++) {
            Car car = dataManager.getCollection().get(i);
            System.out.println(AnsiColor.YELLOW.colorize((i + 1) + ".") + " " + car);
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Вывести текущую коллекцию";
    }
}
