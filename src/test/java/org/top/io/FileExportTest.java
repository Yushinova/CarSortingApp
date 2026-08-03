package org.top.io;

import java.io.File;
import java.util.List;

import org.top.collection.CustomList;
import org.top.model.Car;
import org.top.util.Assert;

public final class FileExportTest {
    private static final String TEST_FILE_PATH = "test_integration_append.txt";

    public static void run() {
        testFileDataIOAppendAndReadIntegration();
    }

    private static void testFileDataIOAppendAndReadIntegration() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) file.delete();

        CarDataConverter converter = new CarDataConverter();
        FileDataIO<Car> ioEngine = new FileDataIO<>(TEST_FILE_PATH, converter);

        List<Car> writeList1 = new CustomList<>();
        writeList1.add(new Car("Toyota", "Camry", 2020, "Red", 200, 30000.0, false));
        
        List<Car> writeList2 = new CustomList<>();
        writeList2.add(new Car("BMW", "X5", 2022, "Black", 250, 45000.0, true));

        try {
            ioEngine.write(writeList1, true);
            ioEngine.write(writeList2, true);

            List<Car> readList = new CustomList<>();
            ioEngine.read(readList, 2);

            boolean integrationSuccess = readList.size() == 2 
                    && "Toyota".equals(readList.get(0).getBrand()) 
                    && "BMW".equals(readList.get(1).getBrand());

            Assert.assertThat(integrationSuccess, "Фича 13, 14 (Доп. 2): Интеграционный тест FileDataIO и CarDataConverter в режиме Append");
        } finally {
            if (file.exists()) file.delete();
        }
    }
}
