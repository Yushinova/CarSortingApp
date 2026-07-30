package org.top.thread;

import java.util.List;

public class ElementCounter<T> implements Runnable {
    private final List<T> list;
    private final int startIndex;
    private final int endIndex;
    private final T element;
    private int count = 0;

    public ElementCounter(List<T> list, int startIndex, int endIndex, T element) {
        this.list = list;
        this.element = element;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            T current = list.get(i);
            if (current != null && current.equals(element))
                count++;
        }
    }

    public int getCount() {
        return this.count;
    }
}
