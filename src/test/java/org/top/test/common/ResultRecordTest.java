package org.top.test.common;

import org.top.menu.util.Result;
import org.top.test.util.Assert;

public final class ResultRecordTest {
    public static void run() {
        testResultSuccessValue();
        testResultFailureThrowsException();
    }

    private static void testResultSuccessValue() {
        Result<String> result = Result.success("ValidData");
        Assert.assertThat(result.isSuccess() && "ValidData".equals(result.value()), "Result Record: Успешный результат возвращает значение");
    }

    private static void testResultFailureThrowsException() {
        Result<String> result = Result.failure("Error context");
        Assert.assertThrows(result::value, "Result Record: Защита геттера выбрасывает IllegalStateException при ошибке");
    }
}
