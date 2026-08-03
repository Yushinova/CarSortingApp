package org.top.menu.state;

import org.top.menu.common.Result;

public final class BackState implements MenuState {
    @Override
    public Result<Boolean> handle() {
        return Result.success(false);
    }

    @Override
    public String getDescription() {
        return "Назад (в предыдущее меню)";
    }
}
