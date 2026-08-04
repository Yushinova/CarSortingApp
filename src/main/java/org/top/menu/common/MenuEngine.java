package org.top.menu.common;

import java.util.Map;
import java.util.Objects;

import org.top.menu.state.MenuState;

public final class MenuEngine {
    private final InputValidator validator;

    public MenuEngine(InputValidator validator) {
        this.validator = Objects.requireNonNull(validator);
    }

    public void run(String title, Map<Integer, MenuState> actions) {
        boolean keepRunning = true;

        while (keepRunning) {
            Screen.clear();

            System.out.println(AnsiColor.CYAN.colorize("\n========== " + title + " =========="));
            actions.forEach((key, state) -> 
                System.out.println(AnsiColor.GREEN.colorize(key + ".") + " " + state.getDescription()));
            
            System.out.print("Выберите действие: ");

            try {
                int choice = validator.readInt();
                MenuState state = actions.get(choice);

                if (state == null) {
                    System.out.println(AnsiColor.RED.colorize("Ошибка: Неверный выбор. Попробуйте снова."));
                    System.out.println(AnsiColor.YELLOW.colorize("Нажмите Enter для продолжения..."));
                    validator.readString();
                    continue;
                }

                Result<Boolean> result = state.handle();
                
                if (result.isFailure()) {
                    System.out.println(AnsiColor.RED.colorize("[Ошибка]: " + result.errorMessage()));
                    System.out.println(AnsiColor.YELLOW.colorize("Нажмите Enter для продолжения..."));
                    validator.readString();
                } else {
                    keepRunning = result.value();
                }
            } catch (Exception e) {
                System.out.println(AnsiColor.RED.colorize("[Критический сбой]: " + e.getMessage()));
                System.out.println(AnsiColor.YELLOW.colorize("Нажмите Enter для продолжения..."));
                validator.readString();
            }
        }
    }
}
