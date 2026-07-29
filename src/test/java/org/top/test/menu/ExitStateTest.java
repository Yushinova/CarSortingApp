package org.top.test.menu;

import org.top.menu.state.ExitState;
import org.top.menu.state.MenuState;
import org.top.test.util.Assert;

public final class ExitStateTest {
    public static void run() {
        testExitStateReturnsFalse();
    }

    private static void testExitStateReturnsFalse() {
        MenuState exitState = new ExitState();
        boolean result = exitState.handle();
        Assert.assertThat(!result, "Фича 4 (ExitState): Метод выхода возвращает false для корректной остановки цикла");
    }
}
