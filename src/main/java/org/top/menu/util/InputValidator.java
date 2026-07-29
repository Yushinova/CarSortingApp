package org.top.menu.util;

import java.util.Scanner;

public final class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String readString() {
        return scanner.nextLine().trim();
    }

    public Result<Integer> validateMenuChoice(int choice, int min, int max) {
        if (choice < min || choice > max) {
            return Result.failure("Выберите пункт в диапазоне от " + min + " до " + max);
        }
        return Result.success(choice);
    }

    public Result<Integer> validateSize(int size) {
        if (size <= 0) {
            return Result.failure("Размер коллекции должен быть строго больше нуля!");
        }
        return Result.success(size);
    }
}
