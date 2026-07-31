package org.top.menu.state;

import org.top.util.Assert;

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
