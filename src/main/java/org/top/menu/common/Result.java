package org.top.menu.common;

public record Result<T>(T value, String errorMessage, boolean isSuccess) {

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(null, errorMessage, false);
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    @Override
    public T value() {
        if (!isSuccess) {
            throw new IllegalStateException();
        }
        return value;
    }
}
