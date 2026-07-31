package org.top.menu.state;

import org.top.data.CarDataManager;
import org.top.io.CarDataConverter;
import org.top.io.FileDataIO;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.model.Car;

public final class SaveFileState implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;
    private final CarDataConverter converter;

    public SaveFileState(CarDataManager dataManager, InputValidator validator, CarDataConverter converter) {
        this.dataManager = dataManager;
        this.validator = validator;
        this.converter = converter;
    }

    @Override
    public boolean handle() {
        System.out.println(AnsiColor.CYAN.colorize("\n--- Запись в файл ---"));
        System.out.print("Введите имя файла для сохранения данных: ");
        String path = validator.readString();

        boolean appendMode = false;
        while (true) {
            System.out.print("Режим записи (1 - Перезаписать, 2 - Добавить в конец): ");
            Result<Integer> res = validator.validateConfirmation(validator.readInt());
            if (res.isFailure()) {
                System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + res.errorMessage()));
                continue;
            }
            if (res.value() == 2) {
                appendMode = true;
            }
            break;
        }

        new FileDataIO<Car>(path, converter).write(dataManager.getCollection(), appendMode);
        System.out.println(AnsiColor.GREEN.colorize("Данные успешно записаны."));
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 2] Записать результат в файл";
    }
}
