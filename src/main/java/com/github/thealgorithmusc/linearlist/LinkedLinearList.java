package com.github.thealgorithmusc.linearlist;

public class LinkedLinearList<E> extends AbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }

        size++;

        return true;
    }

    @Override
    public void add(int index, E e) {
        rangeCheckForAdd(index);

        if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }

            Node<E> temp = curNode.next;
            curNode.next = new Node<>(e);
            (curNode.next).next = temp;
        }
    }

    public boolean addFirst(E e) {
        Node<E> newNode = new Node<>(e);

        Node<E> oldHead = head;
        head = newNode;
        newNode.next = oldHead;

        size++;

        if (tail == null) {
            tail = head;
        }

        return true;
    }

    public boolean addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }

        size++;

        return true;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        Node<E> curNode = head;
        while (curNode != null) {
            if (e.equals(curNode.item)) {
                return true;
            }

            curNode = curNode.next;
        }

        return false;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        if (index == 0) {
            return getFirst();
        } else if (index == size) {
            return getLast();
        } else {
            Node<E> curNode = head;
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }

            return curNode.item;
        }
    }

    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.item;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.item;
        }
    }

    @Override
    public int indexOf(E e) {
        int pos = 0;
        Node<E> curNode = head;
        while (curNode != null) {
            if (e.equals(curNode.item)) {
                return pos;
            }

            curNode = curNode.next;
            pos++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int pos = 0;
        int lastPos = -1;
        Node<E> curNode = head;
        while (curNode != null) {
            if (e.equals(curNode.item)) {
                lastPos = pos;
            }

            curNode = curNode.next;
            pos++;
        }

        return lastPos;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    public E removeFirst() {
        return null;
    }

    public E removeLast() {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        Node() {
        }

        Node(E element) {
            this.item = element;
            this.next = null;
        }
    }

}
