package org.top.sorting;

import org.top.strategy.Sorter;
import org.top.model.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSublistCarSorter implements Sorter<Car> {
    private final Sorter<Car> sorter;
    private final Filter filter;

    public AbstractSublistCarSorter(Sorter<Car> sorter, Filter filter){
        if (sorter == null) {
            throw new IllegalArgumentException("Сортировщик не может быть null");
        }

        this.sorter = sorter;
        this.filter = filter;
    }
    @Override
    public final List<Car> sort(List<Car> list, Comparator<? super Car> comparator) {
        return sort(list, comparator, Order.DIRECT);
    }

    @Override
    public final List<Car> sort(List<Car> list, Comparator<? super Car> comparator, Order order) {
        List<Car> sublist = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            Car car = list.get(i);
            if (pass(car)) {
                sublist.add(car);
                indexes.add(i);
            }
        }

        if (sublist.size() > 1) {
            sorter.sort(sublist,comparator, order);
            for (int i = 0; i < sublist.size(); i++){
                list.set(indexes.get(i), sublist.get(i));
            }
        }

        return list;
    }

    private boolean pass(Car car){
        return switch (filter) {
            case EVEN -> (getValue(car) % 2 == 0);
            case ODEN -> (getValue(car) % 2 == 1);
            default -> throw new IllegalStateException("Неверное состояние фильтра");
        };
    }

    protected abstract Integer getValue(Car car);

    public enum Filter{
        EVEN,
        ODEN
    }
}
