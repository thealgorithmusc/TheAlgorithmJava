package com.github.thealgorithmusc.linearlist;

public abstract class AbstractList<E> implements ILinearList<E> {

    protected int size = 0;

    protected AbstractList() {

    }

    protected AbstractList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Check whether the index is valid
     *
     * @param index
     */
    protected void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Check whether the index is valid for add operation
     *
     * @param index
     */
    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Return out of bound message
     *
     * @param index
     * @return
     */
    protected String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

}
