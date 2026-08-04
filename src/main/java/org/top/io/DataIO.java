package org.top.io;

import java.io.IOException;
import java.util.List;

public interface DataIO<T> {
    void read(List<T> items, int countRecordRead) throws IOException;

    void write(List<T> items, boolean append) throws IOException;
}
