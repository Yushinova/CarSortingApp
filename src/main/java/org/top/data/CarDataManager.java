package org.top.data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.top.model.Car;

public final class CarDataManager implements CarDataService {
    private final List<Car> list;

    public CarDataManager(List<Car> list) {
        this.list = Objects.requireNonNull(list);
    }

    @Override
    public List<Car> getCollection() {
        return list;
    }

    @Override
    public void add(Stream<Car> cars) {
        cars.forEach(list::add);
    }

    @Override
    public void clearCollection() {
        this.list.clear();
    }
}
