package org.top.io;

public interface DataConverter<T> {
    String toString(T item);

    T fromString(String line);
}
