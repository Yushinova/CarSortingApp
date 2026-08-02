package org.top;

import org.top.builder.CarBuilderTest;
import org.top.collection.CustomListTest;
import org.top.data.CarDataManagerTest;
import org.top.io.CarDataConverterTest;
import org.top.io.FileExportTest;
import org.top.menu.common.AnsiColor;
import org.top.menu.common.InputValidatorTest;
import org.top.menu.common.ResultRecordTest;
import org.top.menu.state.ExitStateTest;
import org.top.model.BoundaryValidationTest;
import org.top.model.CarComparatorTest;
import org.top.sorting.EvenOddSortTest;
import org.top.sorting.SortNegativeTest;
import org.top.sorting.SortServiceTest;
import org.top.thread.ThreadCountTest;
import org.top.util.Assert;

public final class ManualTester {
    public static void main(String[] args) {
        System.out.println(AnsiColor.CYAN.colorize("=== ЗАПУСК ПОЛНОЙ ИНТЕГРАЦИОННОЙ СИНХРОНИЗАЦИИ ТЕСТОВ ==="));

        CarBuilderTest.run();

        CustomListTest.run();

        CarDataManagerTest.run();

        CarDataConverterTest.run();
        FileExportTest.run();

        InputValidatorTest.run();
        ResultRecordTest.run();
        ExitStateTest.run();
        
        BoundaryValidationTest.run();
        CarComparatorTest.run();
        
        EvenOddSortTest.run();
        SortServiceTest.run();
        SortNegativeTest.run();
        
        ThreadCountTest.run();

        System.out.println(AnsiColor.CYAN.colorize("==========================================="));
        System.out.printf("ИТОГО ТЕСТОВ: Успешно: " + AnsiColor.GREEN.colorize("%d") + " | Провалено: " + AnsiColor.RED.colorize("%d") + "%n", 
                Assert.getPassedCount(), Assert.getFailedCount());
        System.out.println(AnsiColor.CYAN.colorize("==========================================="));
        
        if (Assert.getFailedCount() > 0) {
            System.exit(1);
        }
    }
}
