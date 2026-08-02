package org.top.strategy;

import java.util.List;

public interface FillStrategy<T> {
    List<T> fill(List<T> list, int size);
}
