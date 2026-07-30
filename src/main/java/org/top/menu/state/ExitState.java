package org.top.menu.state;

import org.top.menu.ui.AnsiColor;

public final class ExitState implements MenuState {
    @Override
    public boolean handle() {
        System.out.println(AnsiColor.GREEN.colorize("\nВыход из программы. До свидания!"));
        return false;
    }

    @Override
    public String getDescription() {
        return "Выход из приложения";
    }
}
