package org.top.menu.state;

import java.util.List;

import org.top.data.CarDataManager;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.model.Car;
import org.top.thread.ThreadManager;

public final class ThreadCountState implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;

    public ThreadCountState(CarDataManager dataManager, InputValidator validator) {
        this.dataManager = dataManager;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        List<Car> currentList = dataManager.getCollection();
        if (currentList == null || currentList.isEmpty()) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка]: Коллекция пуста. Сначала заполните её!"));
            return true;
        }

        int targetIndex;
        while (true) {
            System.out.print("Введите индекс элемента N для подсчета (1-" + currentList.size() + "): ");
            Result<Integer> indexResult = validator.validateMenuChoice(validator.readInt(), 1, currentList.size());
            if (indexResult.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + indexResult.errorMessage()));
                continue;
            }
            targetIndex = indexResult.value() - 1;
            break;
        }

        Car targetElement = currentList.get(targetIndex);

        int threadCount;
        while (true) {
            System.out.print("Введите количество потоков для вычислений: ");
            Result<Integer> threadResult = validator.validateSize(validator.readInt());
            if (threadResult.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + threadResult.errorMessage()));
                continue;
            }
            threadCount = threadResult.value();
            break;
        }

        int totalOccurrences = ThreadManager.multiThreadCounter(currentList, targetElement, threadCount);

        System.out.println(AnsiColor.GREEN.colorize("\n[Успех]: Многопоточный подсчет завершен!"));
        System.out.println("Порядковый номер выбранного элемента: " + (targetIndex + 1));
        System.out.println("Элемент: " + targetElement);
        System.out.println("Всего точных совпадений в коллекции: " + AnsiColor.GREEN.colorize(String.valueOf(totalOccurrences)));
        
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 4] Многопоточный подсчет элемента N (выбор по номеру)";
    }
}
