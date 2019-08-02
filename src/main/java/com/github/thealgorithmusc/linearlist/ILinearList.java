package com.github.thealgorithmusc.linearlist;

public interface ILinearList<E> {

    /**
     * Add a new element at the end of this list
     *
     * @param e
     */
    boolean add(E e);

    /**
     * Add a new element at the specified index in this list
     *
     * @param index
     * @param e
     */
    void add(int index, E e);

    /**
     * Clear the list
     */
    void clear();

    /**
     * Return true if this list contains the element
     *
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * Return the element from this list at the specified index
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * Return the index of the first matching element in this list. Return -1 if no match
     *
     * @param e
     * @return
     */
    int indexOf(E e);

    /**
     * Return the index of the last matching element in this list. Return -1 if no match
     *
     * @param e
     * @return
     */
    int lastIndexOf(E e);

    /**
     * Return true if this list contains no elements
     *
     * @return
     */
    boolean isEmpty();

    /**
     * Remove the first occurrence of the element o from this list.
     * Shift any subsequent elements to the left.
     * Return true if the element is deleted.
     *
     * @param e
     * @return
     */
    boolean remove(E e);

    /**
     * Remove the element at the specified position in this list.
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     *
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * Replace the element at the specified position in this list
     * with the specified element and return the new set.
     *
     * @param index
     * @param e
     * @return
     */
    E set(int index, E e);


    /**
     * Return the number of elements in this list.
     *
     * @return
     */
    int size();

}
