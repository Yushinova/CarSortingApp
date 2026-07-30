package org.top.io;

import java.util.List;

public interface DataIO<T> {
    void write(List<T> items, boolean append);

    void read(List<T> items);
}
