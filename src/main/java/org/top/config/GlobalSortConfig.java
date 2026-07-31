package org.top.config;

import org.top.sorting.MergeSorter;
import org.top.strategy.Sorter;

public class GlobalSortConfig {
    private static GlobalSortConfig instance;
    private Sorter<?> currentSorter;

    private GlobalSortConfig() {
        this.currentSorter = new MergeSorter<>();
    }
    public static GlobalSortConfig getInstance() {
        if (instance == null) {
            instance = new GlobalSortConfig();
        }
        return instance;
    }

    public void setSorter(Sorter<?> sorter) {
        this.currentSorter = sorter;
    }

    @SuppressWarnings("unchecked")
    public <T> Sorter<T> getSorter() {
        return (Sorter<T>) currentSorter;
    }
}
