package org.top.strategy;

import org.top.model.Car;
import java.util.List;

public interface FillStrategy {
    void fill(List<Car> list, int size);
}
