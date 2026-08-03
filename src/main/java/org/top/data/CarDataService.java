package org.top.data;

import java.util.List;
import java.util.stream.Stream;

import org.top.model.Car;

public interface CarDataService {
    List<Car> getCollection();
    void add(Stream<Car> cars);
    void clearCollection();
}
