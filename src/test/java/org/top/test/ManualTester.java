package org.top.test;

import org.top.menu.ui.AnsiColor;
import org.top.test.common.ResultRecordTest;
import org.top.test.menu.ExitStateTest;
import org.top.test.menu.InputValidatorTest;
import org.top.test.model.*;
import org.top.test.io.FileExportTest;
import org.top.test.util.Assert;

public final class ManualTester {
    public static void main(String[] args) {
        System.out.println(AnsiColor.CYAN.colorize("=== ЗАПУСК ВСЕХ АВТОНОМНЫХ ТЕСТОВ ПРИЛОЖЕНИЯ ==="));

        CarBuilderTest.run();
        BoundaryValidationTest.run();
        CarComparatorTest.run();
        SortServiceTest.run();
        EvenOddSortTest.run();
        
        ExitStateTest.run();
        InputValidatorTest.run();
        ResultRecordTest.run();
        
        FileExportTest.run();

        System.out.println(AnsiColor.CYAN.colorize("==========================================="));
        System.out.printf("ИТОГО ТЕСТОВ: Успешно: " + AnsiColor.GREEN.colorize("%d") + " | Провалено: " + AnsiColor.RED.colorize("%d") + "%n", 
                Assert.getPassedCount(), Assert.getFailedCount());
        System.out.println(AnsiColor.CYAN.colorize("==========================================="));
    }
}
