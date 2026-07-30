package org.top.sorting;

public class ShuttleSorter<T> extends AbstractSorter<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < list.size(); i++){
            if (isNotSortOrder(list.get(i - 1),list.get(i))){
                swap(i - 1, i);
                for (int z = i - 1; (z - 1) >= 0; z--){
                    if (isNotSortOrder(list.get(z - 1),list.get(z))) {
                        swap(z - 1, z);
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }
}
