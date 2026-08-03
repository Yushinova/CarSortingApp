package org.top.menu.common;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public final class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
    }

    public int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        } catch (NoSuchElementException e) {
            System.out.println(AnsiColor.RED.colorize("\n[Программа прервана пользователем (Ctrl+C)]. Выход..."));
            System.exit(0);
            return -1;
        }
    }

    public String readString() {
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println(AnsiColor.RED.colorize("\n[Программа прервана пользователем (Ctrl+C)]. Выход..."));
            System.exit(0);
            return "";
        }
    }

    public Result<Integer> validateMenuChoice(int choice, int min, int max) {
        if (choice < min || choice > max) {
            return Result.failure("Выберите пункт в диапазоне от " + min + " до " + max + ".");
        }
        return Result.success(choice);
    }

    public Result<Integer> validateSize(int size) {
        if (size <= 0) {
            return Result.failure("Размер коллекции должен быть больше нуля.");
        }
        return Result.success(size);
    }

    public Result<Integer> validateDirection(int choice) {
        if (choice != 1 && choice != 2) {
            return Result.failure("Неверный выбор. Введите 1 для возрастания или 2 для убывания.");
        }
        return Result.success(choice);
    }

    public Result<Integer> validateConfirmation(int choice) {
        if (choice != 1 && choice != 2) {
            return Result.failure("Неверный выбор. Введите 1 (Да) или 2 (Нет).");
        }
        return Result.success(choice);
    }
}
