package org.top.data;

import java.util.List;

import org.top.model.Car;

public final class CarDataManager implements CarDataService {
    private List<Car> list;

    public CarDataManager(List<Car> list) {
        this.list = list;
    }

    @Override
    public List<Car> getCollection() {
        return list;
    }

    @Override
    public void setCollection(List<Car> list) {
        this.list = list;
    }
}
