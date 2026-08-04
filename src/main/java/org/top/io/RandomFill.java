package org.top.io;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.model.CarPreset;
import org.top.strategy.FillStrategy;

public class RandomFill implements FillStrategy<Car> {

    private final Random random = new Random();

    @Override
    public List<Car> fill(List<Car> list, int size) {
        CarPreset[] presets = CarPreset.values();

        Stream.generate(() -> {
                    CarPreset preset = presets[random.nextInt(presets.length)];
                    return new CarBuilder()
                            .setBrand(preset.getBrand())
                            .setModel(preset.getModel())
                            .setYear(preset.getYear())
                            .setColor(preset.getColor())
                            .setPower(preset.getPower())
                            .setPrice(preset.getPrice())
                            .setIsNew(preset.isNew())
                            .build();
                })
                .limit(size)
                .filter(Objects::nonNull)
                .forEach(list::add);

        return list;
    }
}
