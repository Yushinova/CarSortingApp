package org.top.menu.common;

public final class Screen {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    public static void clear() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }
}
