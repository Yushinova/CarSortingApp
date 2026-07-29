package org.top.sorting;

public class BubbleSorter<T> extends AbstractSorter<T> {
    @Override
    protected void sort() {
        boolean needIteration;
        do {
            needIteration = false;
            for (int i = 1; i < list.size(); i++) {
                if (isNotSortOrder(list.get(i - 1), list.get(i))) {
                    swap(i - 1, i);
                    needIteration = true;
                }
            }
        } while (needIteration);
    }
}
