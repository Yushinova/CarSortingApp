package org.top.test.util;

import org.top.menu.ui.AnsiColor;

public final class Assert {
    private static int passedCount = 0;
    private static int failedCount = 0;

    private Assert() {}

    public static void assertThat(boolean condition, String testName) {
        if (condition) {
            System.out.println(AnsiColor.GREEN.colorize("[PASS] ") + testName);
            passedCount++;
        } else {
            System.out.println(AnsiColor.RED.colorize("[FAIL] ") + testName);
            failedCount++;
        }
    }

    public static void assertThrows(Runnable runnable, String testName) {
        try {
            runnable.run();
            System.out.println(AnsiColor.RED.colorize("[FAIL] ") + testName);
            failedCount++;
        } catch (IllegalStateException e) {
            System.out.println(AnsiColor.GREEN.colorize("[PASS] ") + testName);
            passedCount++;
        } catch (Exception e) {
            System.out.println(AnsiColor.RED.colorize("[FAIL] ") + testName);
            failedCount++;
        }
    }

    public static int getPassedCount() { return passedCount; }
    public static int getFailedCount() { return failedCount; }
}
