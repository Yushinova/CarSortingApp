package org.top.io;

import org.top.builder.CarBuilder;
import org.top.model.Car;
import org.top.model.CarPreset;
import org.top.strategy.FillStrategy;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomFill implements FillStrategy {

    private final Random random = new Random();

    @Override
    public CustomList fill(CustomList list, int size) {
        CarPreset[] presets = CarPreset.values();

        CustomList generated = Stream.generate(() -> {
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
                .collect(Collectors.toList());

        list.addAll(generated);
        return list;
    }
}


