package org.top.io;

import org.top.builder.CarBuilder;
import org.top.model.Car;

public class CarDataConverter implements DataConverter<Car> {
    private final String DELIMITER = ";";
    private final int COUNT_FIELDS = 7;

    @Override
    public String toString(Car item) {
        if (item == null)
            return "";

        String[] carFields = new String[COUNT_FIELDS];

        carFields[0] = item.getBrand();
        carFields[1] = item.getModel();
        carFields[2] = String.valueOf(item.getYear());
        carFields[3] = item.getColor();
        carFields[4] = String.valueOf(item.getPower());
        carFields[5] = String.valueOf(item.getPrice());
        carFields[6] = String.valueOf(item.isNew());

        return String.join(DELIMITER, carFields);
    }

    @Override
    public Car fromString(String line) {
        if (line == null || line.trim().isEmpty())
            return null;

        String[] carFields = line.split(DELIMITER);
        if (carFields.length != COUNT_FIELDS)
            return null;

        return new CarBuilder()
                .setBrand(carFields[0])
                .setModel(carFields[1])
                .setYear(Integer.parseInt(carFields[2]))
                .setColor(carFields[3])
                .setPower(Integer.parseInt(carFields[4]))
                .setPrice(Double.parseDouble(carFields[5]))
                .setIsNew(Boolean.parseBoolean(carFields[6]))
                .build();
    }

}
