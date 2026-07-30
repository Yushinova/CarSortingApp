package org.top.menu.state;

import org.top.menu.ui.AnsiColor;
import org.top.menu.util.InputValidator;

public final class ThreadCountState implements MenuState {
    private final InputValidator validator;

    public ThreadCountState(InputValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean handle() {
        System.out.print(AnsiColor.CYAN.colorize("\nВведите значение N для подсчета: "));
        String n = validator.readString();
        System.out.println(AnsiColor.GREEN.colorize("[Потоки] Передано значение: " + n));
        return true;
    }

    @Override
    public String getDescription() {
        return "[Доп. 4] Многопоточный подсчет элементов N";
    }
}
