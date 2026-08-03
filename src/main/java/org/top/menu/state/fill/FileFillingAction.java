package org.top.menu.state.fill;

import java.util.Objects;

import org.top.data.CarDataService;
import org.top.io.CarDataConverter;
import org.top.io.FileDataIO;
import org.top.menu.common.InputValidator;
import org.top.menu.common.Result;
import org.top.menu.state.MenuState;

public final class FileFillingAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;
    private final CarDataConverter converter;

    public FileFillingAction(CarDataService dataService, InputValidator validator, CarDataConverter converter) {
        this.dataService = Objects.requireNonNull(dataService);
        this.validator = Objects.requireNonNull(validator);
        this.converter = Objects.requireNonNull(converter);
    }

    @Override
    public Result<Boolean> handle() {
        System.out.print("Введите путь к текстовому файлу для чтения: ");
        String path = validator.readString();
        
        System.out.print("Введите длину коллекции чтения из файла: ");
        Result<Integer> sizeResult = validator.validateSize(validator.readInt());

        if (sizeResult.isFailure()) {
            return Result.failure(sizeResult.errorMessage());
        }

        dataService.clearCollection();

        try {
            new FileDataIO<>(path, converter)
                .read(dataService.getCollection(), sizeResult.value());
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure("Ошибка чтения: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Прочитать данные из текстового файла";
    }
}
