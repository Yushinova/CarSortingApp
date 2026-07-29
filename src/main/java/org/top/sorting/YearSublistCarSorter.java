package org.top.sorting;

import org.top.model.Car;
import org.top.strategy.Sorter;

public class YearSublistCarSorter extends AbstractSublistCarSorter {
    public YearSublistCarSorter(Sorter<Car> sorter, Filter filter) {
        super(sorter, filter);
    }

    @Override
    protected Integer getValue(Car car) {
        return car.getYear();
    }
}
