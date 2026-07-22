package org.top.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.top.model.Car;

public class CarList implements List<Car> {
    private final int START_SIZE = 8;
    private Car[] cars;
    private int lastIndex;
    private int shift = 0;

    public CarList() {
        this.init();
    }

    private void init() {
        this.cars = new Car[START_SIZE];
        this.lastIndex = -1;
        this.shift = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > lastIndex)
            throw new IndexOutOfBoundsException(String.format("Индекс: %d, размер коллекции: %d",
                    index, this.size()));
    }

    @Override
    public int size() {
        return this.lastIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.lastIndex == -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(cars, this.lastIndex + 1);
    }

    @Override
    public boolean contains(Object o) {
        if (this.indexOf(o) >= 0)
            return true;
        return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return new CarIterator();
    }

    private class CarIterator implements Iterator<Car> {
        private int cursor = -1;

        @Override
        public boolean hasNext() {
            return cursor < lastIndex;
        }

        @Override
        public Car next() {
            if (!this.hasNext())
                throw new NoSuchElementException("Машины в списке закончились");
            return cars[cursor++];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < lastIndex)
            return (T[]) Arrays.copyOf(cars, this.lastIndex, a.getClass());
        System.arraycopy(cars, 0, a, 0, this.lastIndex);
        if (this.lastIndex > -1 && a.length > this.lastIndex)
            a[this.lastIndex + 1] = null;
        return a;
    }

    @Override
    public boolean add(Car e) {
        if (lastIndex >= cars.length) {
            shift++;
            cars = Arrays.copyOf(cars, START_SIZE << shift);
        }
        lastIndex++;
        cars[lastIndex] = e;
        return this.contains(e);
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);
        if (index >= 0)
            this.remove(index);
        return this.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object car : c)
            if (!this.contains(car))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Car> c) {
        if (c.isEmpty())
            return false;
        for (Car car : c)
            this.add(car);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Car> c) {
        this.checkIndex(index);
        if (c.isEmpty())
            return false;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty())
            return false;
        for (Object car : c)
            remove(car);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        init();
    }

    @Override
    public Car get(int index) {
        this.checkIndex(index);
        return cars[index];
    }

    @Override
    public Car set(int index, Car element) {
        Car result = null;
        this.checkIndex(index);
        result = cars[index];
        cars[index] = element;
        return result;
    }

    @Override
    public void add(int index, Car element) {
        // Проверить
        this.checkIndex(index);
        cars[index] = element;
    }

    @Override
    public Car remove(int index) {
        Car result = null;
        this.checkIndex(index);
        result = cars[index];
        cars[index] = null;
        for (int i = index; i <= lastIndex; i++)
            cars[i] = cars[i + 1];
        lastIndex--;
        if (shift > 0 && lastIndex < (START_SIZE << (shift - 1))) {
            shift--;
            cars = Arrays.copyOf(cars, START_SIZE << shift);
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i <= lastIndex; i++)
            if (cars[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = lastIndex; i >= 0; i--)
            if (cars[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public ListIterator<Car> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<Car> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<Car> subList(int fromIndex, int toIndex) {
        this.checkIndex(fromIndex);
        this.checkIndex(toIndex);
        return List.of(Arrays.copyOfRange(cars, fromIndex, toIndex));
    }

}
