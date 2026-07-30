package org.top.strategy;

import org.top.model.Car;
import java.util.List;

public interface FillStrategy {
    CustomList fill(CustomList list, int size);
}
