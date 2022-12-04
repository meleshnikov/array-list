package org.example.list;

import java.util.Arrays;
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
    public boolean equals(CustomList otherList) {
        return false;
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
}
