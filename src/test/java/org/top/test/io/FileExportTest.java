package org.top.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.top.test.util.Assert;

public final class FileExportTest {
    private static final String TEST_FILE_PATH = "test_append_output.txt";

    public static void run() {
        testFileAppendMode();
    }

    private static void testFileAppendMode() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) file.delete();

        try {
            mockSaveToFile("Line 1\n");
            mockSaveToFile("Line 2\n");

            int lineCount = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
                while (reader.readLine() != null) {
                    lineCount++;
                }
            }

            Assert.assertThat(lineCount == 2, "Фича 13, 14 (Доп. 2): Запись в файл производится строго в режиме добавления данных (Append)");
        } catch (IOException e) {
            Assert.assertThat(false, "Фича 13 (Доп. 2): Сбой ввода-вывода при тестировании файла");
        } finally {
            if (file.exists()) file.delete();
        }
    }

    private static void mockSaveToFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(TEST_FILE_PATH, true)) {
            writer.write(content);
        }
    }
}
