package org.top.menu.state;

import java.util.Objects;

import org.top.data.CarDataService;
import org.top.io.CarDataConverter;
import org.top.io.FileDataIO;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;

public final class SaveFileState implements MenuState {
    private final CarDataService dataManager;
    private final InputValidator validator;
    private final CarDataConverter converter;

    public SaveFileState(CarDataService dataManager, InputValidator validator, CarDataConverter converter) {
        this.dataManager = Objects.requireNonNull(dataManager);
        this.validator = Objects.requireNonNull(validator);
        this.converter = Objects.requireNonNull(converter);
    }

    @Override
    public Result<Boolean> handle() {
        System.out.println(AnsiColor.CYAN.colorize("\n--- Запись в файл ---"));
        System.out.print("Введите имя файла: ");
        String path = validator.readString();

        System.out.print("Режим записи (1 - Перезаписать, 2 - Добавить в конец): ");
        Result<Integer> modeResult = validator.validateMenuChoice(validator.readInt(), 1, 2);
        
        if (modeResult.isFailure()) {
            return Result.failure(modeResult.errorMessage());
        }

        boolean appendMode = (modeResult.value() == 2);

        new FileDataIO<>(path, converter).write(dataManager.getCollection(), appendMode);
        System.out.println(AnsiColor.GREEN.colorize("Данные успешно записаны."));
        return Result.success(true);
    }

    @Override
    public String getDescription() {
        return "[Доп. 2] Записать результат в файл";
    }
}
