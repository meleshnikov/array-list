package org.example.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;

    private int size;

    public CustomArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal init capacity: " + initialCapacity);
        }
        this.data = new Object[initialCapacity];
    }

    private void grow() {
        int oldCapacity = data.length;
        if (oldCapacity == Integer.MAX_VALUE) {
            throw new OutOfMemoryError(
                    "Required array length is too large");
        }
        int prefGrowth = oldCapacity >> 1;
        int newCapacity = oldCapacity + Math.max(1, prefGrowth);
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        this.data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public E add(E item) {
        Objects.requireNonNull(item, "null not supported");
        if (size == data.length) {
            grow();
        }
        data[size++] = item;
        return item;
    }

    private void checkAddIndexRange(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index: " + index + " - out of bounds: " + size);
        }
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index: " + index + " - out of bounds: " + size);
        }
    }

    @Override
    public E add(int index, E item) {
        checkAddIndexRange(index);
        Objects.requireNonNull(item, "null not supported");
        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = item;
        size++;
        return item;
    }

    @Override
    public E set(int index, E item) {
        checkIndexRange(index);
        Objects.requireNonNull(item, "null not supported");
        E oldValue = (E) data[index];
        data[index] = item;
        return oldValue;
    }

    @Override
    public E remove(E item) {
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public E remove(int index) {
        checkIndexRange(index);
        E oldValue = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size--] = null;
        return oldValue;
    }

    @Override
    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(E item) {
        return indexOfRange(item, 0, size);
    }

    private int indexOfRange(E item, int from, int to) {
        Objects.requireNonNull(item, "null not supported");
        for (int i = from; i < to; i++) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E item) {
        return lastIndexOfRange(item, 0, size);
    }

    private int lastIndexOfRange(E item, int from, int to) {
        Objects.requireNonNull(item, "null not supported");
        for (int i = to - 1; i >= from; i--) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);
        return (E) data[index];
    }

    @Override
    public boolean equals(CustomArrayList<E> otherList) {
        boolean equal = false;
        final int s = size;
        if (equal = (s == otherList.size())) {
            for (int i = 0; i < s; i++) {
                if (!Objects.equals(data[i], otherList.data[i])) {
                    return false;
                }
            }
        }
        return equal;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public E[] toArray() {
        E[] arr = (E[]) new Object[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (E) data[i];
        }
        return arr;
    }

    private static void swap(Object[] arr, int a, int b) {
        Object t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    private boolean less(Object o1, Object o2, Comparator<? super E> c) {
        return c.compare((E) o1, (E) o2) < 0;
    }

    public void bubbleSort(Comparator<? super E> c) {
        boolean isSorted = false;
        for (int i = 0; i < size - 1 && !isSorted; i++) {
            isSorted = true;
            for (int j = 1; j < size - i; j++) {
                if (less(data[j], data[j - 1], c)) {
                    swap(data, j, j - 1);
                    isSorted = false;
                }
            }
        }
    }

    public void insertionSort(Comparator<? super E> c) {
        for (int i = 1; i < size; i++) {
            Object key = data[i];
            int j = i - 1;
            for (; j >= 0 && less(key, data[j], c); j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = key;
        }
    }

    public void selectionSort(Comparator<? super E> c) {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less(data[j], data[min], c)) {
                    min = j;
                }
            }
            swap(data, i, min);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

//    public CustomArrayList<?> copy() {
//        CustomArrayList<?> list = new CustomArrayList<>(size);
//        list.data = Arrays.copyOf(data, size);
//        list.size = size;
//        return list;
//    }
}
