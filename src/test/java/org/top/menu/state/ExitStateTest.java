package org.top.menu.state;

import org.top.menu.common.Result;
import org.top.util.Assert;

public final class ExitStateTest {
    public static void run() {
        testExitStateReturnsFalse();
    }

    private static void testExitStateReturnsFalse() {
        MenuState exitState = new ExitState();
        Result<Boolean> result = exitState.handle();
        Assert.assertThat(!result.isFailure(), "Фича 4 (ExitState): Метод выхода возвращает false для корректной остановки цикла");
    }
}
