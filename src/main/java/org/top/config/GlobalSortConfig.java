package org.top.config;

import java.util.Objects;

import org.top.model.Car;
import org.top.strategy.Sorter;

public final class GlobalSortConfig {
    private Sorter<Car> sorter;

    public GlobalSortConfig(Sorter<Car> sorter) {
        setSorter(sorter);
    }

    public Sorter<Car> getSorter() {
        return sorter;
    }

    public void setSorter(Sorter<Car> sorter) {
        this.sorter = Objects.requireNonNull(sorter);
    }
}
