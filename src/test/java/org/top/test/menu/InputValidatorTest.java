package org.top.test.menu;

import java.util.Scanner;

import org.top.menu.util.InputValidator;
import org.top.menu.util.Result;
import org.top.test.util.Assert;

public final class InputValidatorTest {
    public static void run() {
        testSizeValidationWithValidData();
        testSizeValidationWithZeroOrNegative();
    }

    private static void testSizeValidationWithValidData() {
        InputValidator validator = new InputValidator(new Scanner(""));
        Result<Integer> result = validator.validateSize(5);
        Assert.assertThat(result.isSuccess() && result.value() == 5, "Фича 6 (InputValidator): Валидация корректной длины массива");
    }

    private static void testSizeValidationWithZeroOrNegative() {
        InputValidator validator = new InputValidator(new Scanner(""));
        Result<Integer> resultZero = validator.validateSize(0);
        Result<Integer> resultNegative = validator.validateSize(-10);
        Assert.assertThat(resultZero.isFailure() && resultNegative.isFailure(), "Фича 6 (InputValidator): Отклонение невалидной длины");
    }
}
