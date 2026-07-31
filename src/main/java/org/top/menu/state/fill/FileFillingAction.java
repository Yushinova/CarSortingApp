package org.top.menu.state.fill;

import org.top.data.CarDataManager;
import org.top.io.CarDataConverter;
import org.top.io.FileDataIO;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidator;
import org.top.menu.state.MenuState;
import org.top.model.Car;

public final class FileFillingAction implements MenuState {
    private final CarDataManager dataManager;
    private final InputValidator validator;
    private final CarDataConverter converter;

    public FileFillingAction(CarDataManager dataManager, InputValidator validator, CarDataConverter converter) {
        this.dataManager = dataManager;
        this.validator = validator;
        this.converter = converter;
    }

    @Override
    public boolean handle() {
        System.out.print("Введите путь к текстовому файлу для чтения: ");
        String path = validator.readString();
        
        new FileDataIO<Car>(path, converter).read(dataManager.getCollection());
        
        System.out.println(AnsiColor.GREEN.colorize("Данные успешно прочитаны и добавлены в CustomList."));
        return true;
    }

    @Override
    public String getDescription() {
        return "Прочитать данные из текстового файла";
    }
}
