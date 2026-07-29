package org.top.sorting;

import org.top.model.Car;
import org.top.strategy.Sorter;

public class PowerSublistCarSorter extends AbstractSublistCarSorter {
    public PowerSublistCarSorter(Sorter<Car> sorter, Filter filter) {
        super(sorter, filter);
    }

    @Override
    protected Integer getValue(Car car) {
        return car.getPower();
    }
}
