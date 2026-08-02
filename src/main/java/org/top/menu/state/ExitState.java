package org.top.menu.state;

import org.top.menu.common.AnsiColor;
import org.top.menu.common.Result;

public final class ExitState implements MenuState {
    @Override
    public Result<Boolean> handle() {
        System.out.println(AnsiColor.GREEN.colorize("\nВыход из программы. До свидания!"));
        return Result.success(false);
    }

    @Override
    public String getDescription() {
        return "Выход из приложения";
    }
}
