package org.top.strategy;

import org.top.collection.CustomList;

public interface FillStrategy {
    CustomList fill(CustomList list, int size);
}
