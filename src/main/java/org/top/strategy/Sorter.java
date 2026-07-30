package org.top.strategy;

import java.util.Comparator;
import java.util.List;

public interface Sorter<T> {
    List<T> sort(List<T> list, Comparator<? super T> comparator);
    List<T> sort(List<T> list, Comparator<? super T> comparator, Order order);
    public enum Order {
        DIRECT,
        REVERSE
    }
}
