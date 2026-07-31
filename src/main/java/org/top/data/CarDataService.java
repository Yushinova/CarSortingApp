package org.top.data;

import java.util.List;

import org.top.model.Car;

public interface CarDataService {
    List<Car> getCollection();
    void setCollection(List<Car> list);
}
