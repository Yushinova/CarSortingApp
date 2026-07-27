package org.top.sorting;

import org.top.strategy.Sorter;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractSorter<T> implements Sorter<T> {
    protected List<T> list;
    private Comparator<? super T> comparator;
    private Order order;

    @Override
    public List<T> sort(List<T> list, Comparator<? super T> comparator) {
        return sort(list,comparator, Order.DIRECT);
    }

    @Override
    public List<T> sort(List<T> list, Comparator<? super T> comparator, Order order) {
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException("Список для сортировки не может быть null или пустым");
        }
        if (comparator == null){
            throw new IllegalArgumentException("Компаратор не может быть null");
        }

        this.list = list;
        this.comparator = comparator;
        this.order = order;

        sort();

        return this.list;
    }

    protected void swap(int i1, int i2) {
        T tmp = list.get(i1);
        list.set(i1,list.get(i2));
        list.set(i2,tmp);
    }

    /**Если t1, t2 не следуют в порядке сортировки
     * возвращает true
     * иначе false
     */
    protected boolean isNotSortOrder(T t1, T t2){
        int intFlag = comparator.compare(t1, t2);
        return (order == Order.DIRECT) ? intFlag > 0 : intFlag < 0;
    }

    protected abstract void sort();
}
