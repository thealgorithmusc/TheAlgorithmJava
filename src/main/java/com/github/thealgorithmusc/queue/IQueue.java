package com.github.thealgorithmusc.queue;

public interface IQueue<E> {

    /**
     * Push an element into the queue
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * Remove the first element from the queue
     *
     * @return
     */
    E dequeue();

    /**
     * Get the first element of the queue
     *
     * @return
     */
    E peek();

    /**
     * Get the length of the queue
     *
     * @return
     */
    int size();

    /**
     * Check whether the queue is empty
     */
    boolean isEmpty();

}
