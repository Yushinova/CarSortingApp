package org.top.menu.state.fill;

import java.io.IOException;

import org.top.menu.service.CarDataService;
import org.top.menu.state.MenuState;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;

public final class FileFillingAction implements MenuState {
    private final CarDataService dataService;
    private final InputValidator validator;

    public FileFillingAction(CarDataService dataService, InputValidator validator) {
        this.dataService = dataService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.print("Введите путь к текстовому файлу: ");
        String path = validator.readString();
        try {
            dataService.fillFromFile(path);
            System.out.println(AnsiColor.GREEN.colorize("Данные успешно считаны."));
        } catch (IOException e) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка файла]: " + e.getLocalizedMessage()));
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Прочитать данные из текстового файла";
    }
}
