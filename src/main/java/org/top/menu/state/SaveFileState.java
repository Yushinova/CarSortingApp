package org.top.menu.state;

import java.io.IOException;

import org.top.menu.service.FileExportService;
import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;

public final class SaveFileState implements MenuState {
    private final FileExportService exportService;
    private final InputValidator validator;

    public SaveFileState(FileExportService exportService, InputValidator validator) {
        this.exportService = exportService;
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.println(AnsiColor.CYAN.colorize("\n--- Запись в файл ---"));
        System.out.print("Введите имя файла для добавления данных: ");
        String path = validator.readString();
        try {
            exportService.saveLastResultToFile(path);
            System.out.println(AnsiColor.GREEN.colorize("Данные записаны."));
        } catch (IOException e) {
            System.out.println(AnsiColor.RED.colorize("[Ошибка записи]: " + e.getMessage()));
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 2] Записать результат в файл";
    }
}
