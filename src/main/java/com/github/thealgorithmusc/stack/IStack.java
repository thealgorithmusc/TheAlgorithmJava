package com.github.thealgorithmusc.stack;

public interface IStack<E> {

    /**
     * Push an element into the stack
     *
     * @param e
     */
    void push(E e);

    /**
     * Pop the element on the top of the stack
     *
     * @return
     */
    E pop();

    /**
     * Get the element on the top of the stack
     *
     * @return
     */
    E peek();

    /**
     * Get the length of the stack
     *
     * @return
     */
    int size();

    /**
     * Check whether the stack is empty
     */
    boolean isEmpty();

}
