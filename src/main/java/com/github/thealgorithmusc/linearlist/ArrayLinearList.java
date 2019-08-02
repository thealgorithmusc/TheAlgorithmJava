package com.github.thealgorithmusc.linearlist;

public class ArrayLinearList<E> extends AbstractList<E> {

    public static final int INITIAL_CAPACITY = 16;
    private E[] elementData = (E[]) new Object[INITIAL_CAPACITY];

    public ArrayLinearList() {
    }

    public ArrayLinearList(E[] objects) {
        super(objects);
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        elementData[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e) {
        rangeCheckForAdd(index);
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = e;

        size++;
    }

    /**
     * Create a new larger array, double the current size + 1
     */
    private void ensureCapacity() {
        if (size >= elementData.length) {
            E[] newElementData = (E[]) new Object[size * 2 + 1];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    @Override
    public void clear() {
        elementData = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < elementData.length; i++) {
            if (e.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (e.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        E element = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;

        return element;
    }

    @Override
    public E set(int index, E e) {
        rangeCheck(index);

        E old = elementData[index];
        elementData[index] = e;

        return old;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(elementData[i]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }

}
