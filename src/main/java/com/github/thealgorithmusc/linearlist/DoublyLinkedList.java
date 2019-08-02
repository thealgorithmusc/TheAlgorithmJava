package com.github.thealgorithmusc.linearlist;

// TODO: Need for implementation
public class DoublyLinkedList<E> extends LinkedLinearList<E> {

    private DoublyNode<E> head;
    private DoublyNode<E> tail;

    @Override
    public void add(int index, E e) {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public int lastIndexOf(E e) {
        return 0;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    static class DoublyNode<E> extends LinkedLinearList.Node<E> {
        E item;
        LinkedLinearList.Node<E> prev;
        LinkedLinearList.Node<E> next;

        DoublyNode(E element) {
            super(element);
            this.prev = null;
        }
    }

}
