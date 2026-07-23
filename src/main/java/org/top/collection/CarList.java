package org.top.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.top.model.Car;

public class CarList implements List<Car> {
    private final int START_SIZE = 8;
    private Car[] cars;
    private int lastIndex;

    public int getInnerSize() {
        return this.cars.length;
    }

    public int getLastIndex() {
        return this.lastIndex;
    }

    public CarList() {
        this.init();
    }

    private void init() {
        this.cars = new Car[START_SIZE];
        this.lastIndex = -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.lastIndex)
            throw new IndexOutOfBoundsException(String.format("Индекс: %d, размер коллекции: %d, последний индекс: %d",
                    index, this.size(), this.lastIndex));
    }

    private void checkIndexAdd(int index) {
        if (index < 0 || index > this.lastIndex + 1)
            throw new IndexOutOfBoundsException(String.format("Индекс: %d, размер коллекции: %d, последний индекс: %d",
                    index, this.size(), this.lastIndex));
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
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor <= lastIndex;
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
        if (a.length < size())
            return (T[]) Arrays.copyOf(cars, size(), a.getClass());
        System.arraycopy(cars, 0, a, 0, this.size());
        if (this.lastIndex > -1 && a.length > this.lastIndex)
            a[this.lastIndex + 1] = null;
        return a;
    }

    private void resize(int newLastIndex) {
        int newShift = 0;
        int newSize = START_SIZE;
        while (newLastIndex >= newSize) {
            newShift++;
            newSize = START_SIZE << newShift;
        }
        if (newSize != cars.length)
            cars = Arrays.copyOf(cars, newSize);
    }

    @Override
    public boolean add(Car e) {
        this.resize(++lastIndex);
        cars[lastIndex] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);
        if (index >= 0)
            this.remove(index);
        return index >= 0;
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
        this.checkIndexAdd(index);
        if (c.isEmpty())
            return false;
        int numMoved = lastIndex + 1 - index;
        lastIndex += c.size();
        resize(lastIndex);
        if (numMoved > 0)
            System.arraycopy(cars, index, cars, index + c.size(), numMoved);

        for (Car car : c) {
            cars[index] = car;
            index++;
        }
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
        boolean modified = false;
        for (int i = lastIndex; i >= 0; i--)
            if (!c.contains(cars[i])) {
                this.remove(cars[i]);
                modified = true;
            }
        return modified;
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
        this.checkIndexAdd(index);
        resize(++lastIndex);
        System.arraycopy(cars, index, cars, index + 1, lastIndex - index);
        cars[index] = element;
    }

    @Override
    public Car remove(int index) {
        Car result = null;
        this.checkIndex(index);
        result = cars[index];
        for (int i = index; i < lastIndex; i++)
            cars[i] = cars[i + 1];
        cars[lastIndex] = null;
        resize(--lastIndex);
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i <= lastIndex; i++)
            if (Objects.equals(cars[i], o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = lastIndex; i >= 0; i--)
            if (Objects.equals(cars[i], o))
                return i;
        return -1;
    }

    @Override
    public ListIterator<Car> listIterator() {
        return new CarListIterator(0);
    }

    @Override
    public ListIterator<Car> listIterator(int index) {
        return new CarListIterator(index);
    }

    private class CarListIterator implements ListIterator<Car> {
        private int cursor;
        private int lastCursor = -1;

        public CarListIterator(int index) {
            checkIndexAdd(index);
            this.cursor = index;
        }

        @Override
        public boolean hasNext() {
            return this.cursor <= lastIndex;
        }

        @Override
        public Car next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastCursor = cursor;
            return cars[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public Car previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            lastCursor = --cursor;
            return cars[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastCursor < 0)
                throw new IllegalStateException();
            CarList.this.remove(lastCursor);
            cursor = lastCursor;
            lastCursor = -1;
        }

        @Override
        public void set(Car e) {
            if (lastCursor < 0)
                throw new IllegalStateException();
            cars[lastCursor] = e;
        }

        @Override
        public void add(Car e) {
            int i = cursor;
            CarList.this.add(i, e);
            cursor = i + 1;
            lastCursor = -1;
        }
    }

    @Override
    public List<Car> subList(int fromIndex, int toIndex) {
        this.checkIndex(fromIndex);
        this.checkIndexAdd(toIndex);
        return List.of(Arrays.copyOfRange(cars, fromIndex, toIndex));
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public void sort(Comparator<? super E> c) {
    // // Временное решение: сортируем только заполненную часть массива стандартными
    // средствами Java
    // Arrays.sort((E[]) cars, 0, lastIndex + 1, c);
    // }
}