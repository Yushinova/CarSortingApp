package org.top.strategy;

import java.util.Comparator;
import java.util.List;

public interface Sorter<T> {
    void sort(List<T> list, Comparator<? super T> comparator);
    void sort(List<T> list, Comparator<? super T> comparator, Order order);
    public enum Order {
        DIRECT,
        REVERSE
    }
}
