package org.top.strategy;

import org.top.model.Car;
import java.util.List;

public interface FillStrategy {
    List<Car> fill(List<Car> list, int size);
}
