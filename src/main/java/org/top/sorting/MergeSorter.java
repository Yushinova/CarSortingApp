package org.top.sorting;

public class MergeSorter<T> extends AbstractSorter<T> {
    @Override
    protected void sort() {
        split(0, list.size() - 1);
    }

    private void split(int left, int right){
        if (left > right)
            throw new IllegalArgumentException("Левая граница больше правой");
        if (left == right)
            return;

        int delimiter = left + (right - left) / 2 + 1;
        if (right > (left + 1)){
            split(left, delimiter - 1);
            split(delimiter, right);
        }

        merge(left, right, delimiter);
    }

    private void merge(int left, int right, int delimiter){
        Object[] buffer = new Object[right - left + 1];
        int leftCursor = left;
        int rightCursor = delimiter;
        for (int i = 0; i < buffer.length; i++) {
            if (rightCursor > right){
                buffer[i] = list.get(leftCursor++);
            } else if (leftCursor > delimiter - 1) {
                buffer[i] = list.get(rightCursor++);
            } else if (isNotSortOrder(list.get(leftCursor), list.get(rightCursor))) {
                buffer[i] = list.get(rightCursor++);
            } else {
                buffer[i] = list.get(leftCursor++);
            }
        }
        for (int i = 0; i < buffer.length; i++){
            list.set(left + i, (T) buffer[i]);
        }
    }
}
