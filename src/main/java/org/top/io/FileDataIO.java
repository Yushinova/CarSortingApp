package org.top.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileDataIO<T> implements DataIO<T> {
    private final String filename;
    private final DataConverter<T> converter;

    public FileDataIO(String filename, DataConverter<T> converter) {
        this.filename = filename;
        this.converter = converter;
    }

    @Override
    public void write(List<T> items, boolean append) {
        Path path = Path.of(filename);

        try {

            List<String> lines = items.stream()
                    .map(converter::toString)
                    .toList();
            StandardOpenOption option = append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, option);

        } catch (IOException e) {
            throw new RuntimeException(String.format("Критическая ошибка при работе с файлом '%s'. Текст ошибки: %s",
                    filename, e.getMessage()));
        }
    }

    @Override
    public void read(List<T> items) {
        Path path = Path.of(filename);
        if (!Files.exists(path)) {
            System.err.println(String.format("Ошибка, файл '%s' не существует", filename));
            return;
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {

            lines
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .map(converter::fromString)
                    .filter(Objects::nonNull)
                    .forEach(items::add);

        } catch (IOException e) {
            throw new RuntimeException(String.format("Критическая ошибка при работе с файлом '%s'. Текст ошибки: %s",
                    filename, e.getMessage()));
        }
    }

}
